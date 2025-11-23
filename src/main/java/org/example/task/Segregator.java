package org.example.task;

import org.example.animals.Chordate;
import org.example.animals.Hedgehog;
import org.example.animals.Lynx;
import org.example.animals.Manul;

import java.util.Collection;

public class Segregator {
    public static void segregate(Collection<? extends Chordate> srcCollection,
                                 Collection<? super Hedgehog> collection1,
                                 Collection<? super Manul> collection2,
                                 Collection<? super Lynx> collection3) {
        for (Chordate animal : srcCollection) {
            if (animal instanceof Hedgehog) {
                collection1.add((Hedgehog) animal);
            } else if (animal instanceof Manul) {
                collection2.add((Manul) animal);
            } else if (animal instanceof Lynx) {
                collection3.add((Lynx) animal);
            }
        }
    }
}
