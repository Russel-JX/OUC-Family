package patten.builder;

public class ConcreteBuilder extends Builder{
    private Product product = new Product();

    @Override
    public void buildPartA(int partA) {
        product.setPartA(partA);
    }

    @Override
    public void buildPartB(int partB) {
        product.setPartB(partB);
    }

    @Override
    public Product build() {
        return product;
    }
}
