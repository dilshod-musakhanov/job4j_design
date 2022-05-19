package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsPetr() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename(), is("Petr"));
    }

    @Test
    public void whenAddandFindThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr"));
        Role result = roleStore.findById("10");
        assertNull(result);
    }

    @Test
    public void whenReplaceThenRolenameisMaxim() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr"));
        roleStore.replace("1", new Role("1", "Maxim"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename(), is("Maxim"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr"));
        roleStore.replace("10", new Role("10", "Maxim"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename(), is("Petr"));
    }


    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr"));
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsPetr() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Petr"));
        roleStore.delete("10");
        Role result = roleStore.findById("1");
        assertThat(result.getRolename(), is("Petr"));
    }
}