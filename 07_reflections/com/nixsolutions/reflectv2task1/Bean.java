package com.nixsolutions.reflectv2task1;

public class Bean {
    @Public public String genus;
    @Public protected String origin;
    @Public private String land;
    @Public double proteinsPercent; // package-private access intended.
    @Public private boolean dryProcessed;

    String species; // package-private access intended.
    public double caffeinePercent;
    protected boolean roasted;
    
    public Bean() {
        // Null-argument constructor, as expected for JavaBeans.
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public double getCaffeinePercent() {
        return caffeinePercent;
    }

    public void setCaffeinePercent(double caffeinePercent) {
        this.caffeinePercent = caffeinePercent;
    }

    public double getProteinsPercent() {
        return proteinsPercent;
    }

    public void setProteinsPercent(double proteinsPercent) {
        this.proteinsPercent = proteinsPercent;
    }

    public boolean isRoasted() {
        return roasted;
    }

    public void setRoasted(boolean roasted) {
        this.roasted = roasted;
    }

    public boolean isDryProcessed() {
        return dryProcessed;
    }

    public void setDryProcessed(boolean dryProcessed) {
        this.dryProcessed = dryProcessed;
    }

}
