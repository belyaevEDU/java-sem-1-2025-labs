package org.example;

public class AnnotatedClass {
    public void pub1() {
        System.out.println("pub1");
    }

    @CustomAnnotation(parameter = 2)
    public void pub2() {
        System.out.println("pub2");
    }

    @CustomAnnotation(parameter = 3)
    protected void pro1() {
        System.out.println("pro1");
    }

    protected void pro2() {
        System.out.println("pro2");
    }

    private void pri1() {
        System.out.println("pri1");
    }

    @CustomAnnotation(parameter = 5)
    private void pri2() {
        System.out.println("pri2");
    }

}
