package lock;

//枚举类
public enum CountCountry {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),
    FOUR(4,"赵"),FIVE(5,"魏"),SIX(6, "韩");

    private Integer id;
    private String county;

    private CountCountry(Integer id,String county)
    {
        this.id = id;
        this.county = county;
    }

    public Integer getId() {
        return id;
    }

    public String getCounty() {
        return county;
    }

    public static CountCountry forEach_CountCountry(int index)
    {
        CountCountry[] countCountries = CountCountry.values(); //获得所有元素
        for(CountCountry element : countCountries)
        {
            if(index == element.getId()) //有这个id就返回id对应的值
                return element;
        }
        return null;
    }
}
