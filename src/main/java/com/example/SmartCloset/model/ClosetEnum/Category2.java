package com.example.SmartCloset.model.ClosetEnum;

public enum Category2 {

    //OUTER
    HOOD("후드"),
    COAT("코트"),
    JACKET("재킷"),

    //TOP
    TSHIRT("티셔츠"),
    SHIRT("셔츠"),
    SWEAT("스웨트 셔츠"),
    NEAT("니트"),

    //BOTTOM
    DENIM("데님 팬츠"),
    SHORT("숏 팬츠"),
    JOGER("조거 팬츠"),

    //SHOES
    SHOES("구두"),
    SANDLE("샌들"),
    CONVERSE("컨버스"),
    HIGHTOP("하이탑");

    private String category;
    Category2(String category) { this.category = category; }
    public String getCategory() { return category; }

}
