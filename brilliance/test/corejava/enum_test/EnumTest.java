package corejava.enum_test;

public class EnumTest {


    public static void main(String[] args) {
        System.out.println("——————————————简单枚举——————————————————————————");
        WeekDay tue = WeekDay.Tuesday;
        System.out.println(tue);

        //Enum instant.ordinal() - get index of the Enum instant
        int index = WeekDay.Tuesday.ordinal();
        System.out.println("get index of the Enum instant of "+ WeekDay.Tuesday+", is: "+index);
        //valueOf() - get Enum object by String text
        WeekDay day = WeekDay.valueOf("Friday");
        System.out.println("get Enum object by String text:"+day);
        //枚举switch比较
        switch(tue){
            case Monday : System.out.println("match " + WeekDay.Monday); break;
            case Tuesday : System.out.println("match " + WeekDay.Tuesday); break;
            case Wednesday : System.out.println("match " + WeekDay.Wednesday); break;
            case Thursday : System.out.println("match " + WeekDay.Thursday); break;
            case Friday : System.out.println("match " + WeekDay.Friday); break;
            default: System.out.println("match nothing!"); break;
        }
        //枚举if比较
        if(tue.equals(WeekDay.Tuesday)){
            System.out.println("match tue");
        }
        //循环枚举数组
        for(int i=0;i<WeekDay.values().length;i++){
            System.out.println(WeekDay.values()[i]);
        }

        System.out.println("——————————————复杂枚举——————————————————————————");
        Continent con = Continent.Asia;
        System.out.println(con);
        System.out.println("populatin of "+con.name()+" is:"+con.getPopulation());
    }

    /**
     * @author russell.jiang
     *简单枚举。每个元素只有一个组成
     *不强制要有构造方法(隐式调用无参构造方法)
     */
    public static enum WeekDay{
        Monday, Tuesday, Wednesday, Thursday, Friday
    }

    /**
     * @author russell.jiang
     *复杂枚举。枚举的每个元素由多个部分组成。这样就能让一个枚举元素包含更多的信息。
     *必须要有构造方法。构造方法是为了让每个枚举元素内的各个属性都能初始化，都能被外界访问。
     */
    public static enum Continent{
        Asia("Yellow",20,false),
        Africa("Black",14,false),
        Europe("White",13,true),
        North_America("White",13,true),
        South_America("White",13,true),
        Oceania("White",13,true),
        Antarctica("White",13,true);

        private String skin;
        private int population;
        private boolean developedCountry;

        //枚举元素有多部分组成时，必须有构造方法。为的是能让外界访问每个枚举内的细节
        //构造方法必须不是公开的。目的是枚举的实例只能在定义枚举时创建，就是上面的Asia,Afica等，不能通过外部调用来创建枚举实例。
        Continent(String skin, int population, boolean developedCountry){
            this.skin = skin;
            this.population = population;
            this.developedCountry = developedCountry;
        }
        public String getSkin() {
            return skin;
        }
        public void setSkin(String skin) {
            this.skin = skin;
        }
        public int getPopulation() {
            return population;
        }
        public void setPopulation(int population) {
            this.population = population;
        }
        public boolean isDevelopedCountry() {
            return developedCountry;
        }
        public void setDevelopedCountry(boolean developedCountry) {
            this.developedCountry = developedCountry;
        }
    }
}
