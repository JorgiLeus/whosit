package com.axxes.whosit.persistence.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StaffTest {

    @Test
    public void testGetId(){
        Staff staff = new Staff();
        long id = staff.getId();
        assertThat(staff.getId()).isEqualTo(id);
        System.out.println(id);
    }
}
