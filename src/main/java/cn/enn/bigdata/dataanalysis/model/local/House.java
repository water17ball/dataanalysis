package cn.enn.bigdata.dataanalysis.model.local;

public class House {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house.id
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house.city
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house.picture
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    private String picture;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house.bed
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    private Byte bed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house.location
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    private String location;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house.price
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    private Short price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house.discount_price
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    private Short discountPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house.good_remark
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    private Short goodRemark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house.id
     *
     * @return the value of house.id
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house.id
     *
     * @param id the value for house.id
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house.city
     *
     * @return the value of house.city
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house.city
     *
     * @param city the value for house.city
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house.picture
     *
     * @return the value of house.picture
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public String getPicture() {
        return picture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house.picture
     *
     * @param picture the value for house.picture
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house.bed
     *
     * @return the value of house.bed
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public Byte getBed() {
        return bed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house.bed
     *
     * @param bed the value for house.bed
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public void setBed(Byte bed) {
        this.bed = bed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house.location
     *
     * @return the value of house.location
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house.location
     *
     * @param location the value for house.location
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house.price
     *
     * @return the value of house.price
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public Short getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house.price
     *
     * @param price the value for house.price
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public void setPrice(Short price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house.discount_price
     *
     * @return the value of house.discount_price
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public Short getDiscountPrice() {
        return discountPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house.discount_price
     *
     * @param discountPrice the value for house.discount_price
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public void setDiscountPrice(Short discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house.good_remark
     *
     * @return the value of house.good_remark
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public Short getGoodRemark() {
        return goodRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house.good_remark
     *
     * @param goodRemark the value for house.good_remark
     *
     * @mbg.generated Thu Dec 05 17:04:27 CST 2019
     */
    public void setGoodRemark(Short goodRemark) {
        this.goodRemark = goodRemark;
    }
}