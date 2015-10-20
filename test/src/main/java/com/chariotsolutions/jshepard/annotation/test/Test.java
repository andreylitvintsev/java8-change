package com.chariotsolutions.jshepard.annotation.test;

import com.chariotsolutions.jshepard.annotation.Change;

import java.util.Optional;

public class Test {
  public static void main(String... args) {
    new Test().test();
  }

  private void test() {
    Name testName = null;
    test(testName, "null");
    testName = new Name();
    test(testName, "null");
    testName.setName("TheName");
    test(testName, testName.getName());
  }

  private void test(Name testName, String test3Name) {
    // Should change.
    @Change String name = testName.getName();
    System.out.println("Name1 (should be Changed):" + name);
    // Not a method declaration, so should not change.
    @Change String name2 = "Na.me2";
    System.out.println("Name2 (should be Na.me2):" + name2);
    // Already null safe, so no annotation.
    String name3 = Optional.ofNullable(testName).map(a -> a.getName()).orElse(null);
    System.out.println(String.format("Name3 (should be %s):%s", test3Name, name3));
  }

  private class Name {
    private String name;
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
  }
}
