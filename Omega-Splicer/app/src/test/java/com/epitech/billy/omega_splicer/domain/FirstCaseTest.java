package com.epitech.billy.omega_splicer.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by bichon_b on 3/12/16.
 */
public class FirstCaseTest {

    @Test
    public void test1() {
        assertThat(true, is(true));
    }

    @Test
    public void test2() {
        assertThat(false, is(false));
    }
}
