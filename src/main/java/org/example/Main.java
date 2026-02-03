package org.example;

import org.example.animals.Chordate;
import org.example.animals.*;
import org.example.task.Segregator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("ТЕСТ 1");
        List<Mammal> mammals = new ArrayList<>();
        mammals.add(new Hedgehog());
        mammals.add(new Manul());
        mammals.add(new Lynx());

        List<Erinaceidae> erinaceidaeDest = new ArrayList<>();
        List<Felidae> felidaeDest = new ArrayList<>();
        List<Predator> predatorsDest = new ArrayList<>();

        Segregator.segregate(mammals, erinaceidaeDest, felidaeDest, predatorsDest);

        System.out.println("Ежовые:");
        erinaceidaeDest.stream().forEach(System.out::println);

        System.out.println("Кошачьи:");
        felidaeDest.stream().forEach(System.out::println);

        System.out.println("Хищные:");
        predatorsDest.stream().forEach(System.out::println);


        System.out.println("\nТЕСТ 2");
        List<Predator> predators = new ArrayList<>();
        predators.add(new Manul());
        predators.add(new Lynx());

        List<Chordate> chordatesDest = new ArrayList<>();
        List<Manul> manulsDest = new ArrayList<>();
        List<Felidae> felidaeDest2 = new ArrayList<>();

        Segregator.segregate(predators, chordatesDest, manulsDest, felidaeDest2);

        System.out.println("Хордовые:");
        chordatesDest.stream().forEach(System.out::println);

        System.out.println("Манулы:");
        manulsDest.stream().forEach(System.out::println);

        System.out.println("Кошачьи:");
        felidaeDest2.stream().forEach(System.out::println);


        System.out.println("\nТЕСТ 3");
        List<Erinaceidae> erinaceidaeSrc = new ArrayList<>();
        erinaceidaeSrc.add(new Hedgehog());

        List<Insectivore> insectivoresDest = new ArrayList<>();
        List<Predator> predatorsDest1 = new ArrayList<>();
        List<Predator> predatorsDest2 = new ArrayList<>();

        Segregator.segregate(erinaceidaeSrc, insectivoresDest, predatorsDest1, predatorsDest2);

        System.out.println("Насекомоядные:");
        insectivoresDest.stream().forEach(System.out::println);

        System.out.println("Хищные:");
        predatorsDest1.stream().forEach(System.out::println);

        System.out.println("Хищные:");
        predatorsDest2.stream().forEach(System.out::println);
    }
}