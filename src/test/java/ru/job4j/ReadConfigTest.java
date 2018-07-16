package ru.job4j;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReadConfigTest {

    @Test
    public void whenReadMailRecipientInFileShouldReturnTrue() throws IOException {
        ReadConfig readConfig = new ReadConfig("app.properties");
        assertThat(readConfig.getMail_recipient(), is("rvaleev@elg-sys.com"));
    }

    @Test
    public void whenReadMailYourInFileShouldReturnTrue() throws IOException {
        ReadConfig readConfig = new ReadConfig("app.properties");
        assertThat(readConfig.getMail_your(), is("smih87@yandex.ru"));
    }
}