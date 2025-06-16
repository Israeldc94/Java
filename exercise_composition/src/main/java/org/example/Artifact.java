package org.example;

public class Artifact {
    private String name;
    private Person discoverer;
    private Person curator;
    private int yearOfDiscovery;

    public Artifact(String name, Person discoverer, Person curator, int yearOfDiscovery) {
        this.name = name;
        this.discoverer = discoverer;
        this.curator = curator;
        this.yearOfDiscovery = yearOfDiscovery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getDiscoverer() {
        return discoverer;
    }

    public void setDiscoverer(Person discoverer) {
        this.discoverer = discoverer;
    }

    public Person getCurator() {
        return curator;
    }

    public void setCurator(Person curator) {
        this.curator = curator;
    }

    public int getYearOfDiscovery() {
        return yearOfDiscovery;
    }

    public void setYearOfDiscovery(int yearOfDiscovery) {
        this.yearOfDiscovery = yearOfDiscovery;
    }
}
