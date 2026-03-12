package org.dk.streams.methodReference;

public class Test1 {

    public static void main(String[] args) {

        IAddition addition1 = new IAddition() {
            @Override
            public int sum(int v1, int v2) {
                return v1+v2;
            }
        };
        addition1.sum(1,3);
        IAddition addition2 = Test1::doAddition;
        addition2.sum(1,4);
        IAddition addition = Integer::sum;
        addition.sum(1,2);

        // If you want to pass the reference if it's not a static method then use it like this
        // pass the object along with the parameters (have to change the interface method to int sum(Test1 test1, int v1, int v2);)

//        IAddition iAddition3 = Test1::doAddition;
//        iAddition3.sum(new Test1(), 3,4);
//        or
//        IAddition iAddition4 = new Test1()::doAddition;;
//        iAddition4.sum(3,4);

    }


    static int doAddition(int a, int b){
        return a+b;
    }

//    int doAddition(int a, int b){
//        return a+b;
//    }
}
