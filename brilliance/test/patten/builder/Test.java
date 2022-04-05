package patten.builder;

public class Test {
    public static void main(String[] args){
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct(1,2);
        Product product = builder.build();
        System.out.println(product);

        Computer computer = new Computer.Builder()
                .setCPU("inter-skylake-i7")
                .setGPU("GTX-Titan")
                .setMemoryType("ddr4-2133MHz")
                .setMemorySize(16)
                .setStorageType("ssd")
                .setStorageSize(512)
                .setScreenType("IPS")
                .setScreenSize(28)
                .setOSType("Ubuntu/Window10")
                .create();
        System.out.println(computer);
    }
}
