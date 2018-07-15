package ru.job4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс иммет поля аналогичные файлу настроек.
 * Класс соответствут концепции JavaBean
 *
 * @author Mikhailov Sergey Mikhailov
 * @since 15.07.18
 */
public class ReadConfig {
    private String mail_recipient;
    private String mail_your;
    private String smtp_host;
    private String smtp_port;
    private String mail_username;
    private String mail_password;
    private String mail_subject;
    private String full_name;
    private String phone;

    /**
     * Конструктор
     *
     * @param propFileName название файла
     * @throws IOException если файл пуст
     */
    public ReadConfig(String propFileName) throws IOException {
        Properties properties = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        mail_recipient = properties.getProperty("mail_recipient");
        mail_your = properties.getProperty("mail_your");
        smtp_host = properties.getProperty("smtp_host");
        smtp_port = properties.getProperty("smtp_port");
        mail_username = properties.getProperty("mail_username");
        mail_password = properties.getProperty("mail_password");
        mail_subject = properties.getProperty("mail_subject");
        full_name = properties.getProperty("full_name");
        phone = properties.getProperty("phone");
    }

    public String getMail_recipient() {
        return mail_recipient;
    }

    public void setMail_recipient(String mail_recipient) {
        this.mail_recipient = mail_recipient;
    }

    public String getMail_your() {
        return mail_your;
    }

    public void setMail_your(String mail_your) {
        this.mail_your = mail_your;
    }

    public String getSmtp_host() {
        return smtp_host;
    }

    public void setSmtp_host(String smtp_host) {
        this.smtp_host = smtp_host;
    }

    public String getSmtp_port() {
        return smtp_port;
    }

    public void setSmtp_port(String smtp_port) {
        this.smtp_port = smtp_port;
    }

    public String getMail_username() {
        return mail_username;
    }

    public void setMail_username(String mail_username) {
        this.mail_username = mail_username;
    }

    public String getMail_password() {
        return mail_password;
    }

    public void setMail_password(String mail_password) {
        this.mail_password = mail_password;
    }

    public String getMail_subject() {
        return mail_subject;
    }

    public void setMail_subject(String mail_subject) {
        this.mail_subject = mail_subject;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}