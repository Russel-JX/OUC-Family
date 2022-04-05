package patten.builder;

public abstract class Builder {
    public abstract void buildPartA(int partA);

    public abstract void buildPartB(int partB);

    public abstract Product build();
}
