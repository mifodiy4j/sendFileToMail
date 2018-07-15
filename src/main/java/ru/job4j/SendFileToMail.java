package ru.job4j;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Класс для изменения файла шаблона {@code fileNameTemplate} и
 * его отправки на e-mail
 * Настройки хранятся в файле настроек {@code fileNameProperties}
 *
 * @author Mikhailov Sergey Mikhailov
 * @since 15.07.18
 */
public class SendFileToMail {

    private final String fileNameTemplate;
    private final String fileNameProperties;

    /**
     * Конструктор
     *
     * @param fileNameTemplate название файла шаблона
     * @param fileNameProperties название файла настроек
     */
    public SendFileToMail(String fileNameTemplate, String fileNameProperties) {
        this.fileNameTemplate = fileNameTemplate;
        this.fileNameProperties = fileNameProperties;
    }

    /**
     * Конструктор
     */
    public SendFileToMail() {
        this.fileNameTemplate = "test.ftl";
        this.fileNameProperties = "app.properties";
    }

    /**
     * Метод, который вносит изменения в файл шаблона,
     * вместо ${full_name} в шаблоне {@param full_name}
     * вместо ${phone} в шаблоне {@param phone}
     * @param full_name строка, которая подставится в шаблон {@code fileNameTemplate} вместо ${full_name}
     * @param phone строка, которая подставится в шаблон {@code fileNameTemplate} вместо ${phone}
     * @return шаблон {@code fileNameTemplate} с вставленными значениями
     * @throws IOException
     * @throws TemplateException
     */
    private String readAndChangeTemplate(String full_name, String phone) throws IOException, TemplateException {
        Configuration cfg = new Configuration();

        cfg.setClassForTemplateLoading(SendFileToMail.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate(fileNameTemplate);

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("full_name", full_name);
        templateData.put("phone", phone);

        try (StringWriter out = new StringWriter()) {

            template.process(templateData, out);

            return out.getBuffer().toString();
        }
    }

    /**
     * @throws IOException
     * @throws TemplateException
     */
    private void sendMail() throws IOException, TemplateException {
        ReadConfig readConfig = new ReadConfig(fileNameProperties);
        String str = readAndChangeTemplate(readConfig.getFull_name(), readConfig.getPhone());

        Properties props = new Properties();
        props.put("mail.smtp.host", readConfig.getSmtp_host());
        props.put("mail.smtp.socketFactory.port", readConfig.getSmtp_port());
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", readConfig.getSmtp_port());

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(readConfig.getMail_username(), readConfig.getMail_password());
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(readConfig.getMail_your()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(readConfig.getMail_recipient()));
            message.setSubject(readConfig.getMail_subject());

            message.setText(str);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, TemplateException {
        SendFileToMail sendFileToMail = new SendFileToMail();
        sendFileToMail.sendMail();
    }
}