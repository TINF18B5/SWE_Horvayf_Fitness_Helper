package com.example.fitnesshelper;

import com.example.fitnesshelper.db.DataBaseConnector;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void databaseIsNotNull() {
        assertNotNull(DataBaseConnector.getInstance());
    }
}