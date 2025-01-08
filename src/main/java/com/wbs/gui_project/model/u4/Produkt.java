package com.wbs.gui_project.model.u4;

public class Produkt
{
    private String name;
    private String beschreibung;
    private int menge;
    private double preis;

    public Produkt(String name, String beschreibung, int menge, double preis)
    {
        this.name = name;
        this.beschreibung = beschreibung;
        this.menge = menge;
        this.preis = preis;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getBeschreibung()
    {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung)
    {
        this.beschreibung = beschreibung;
    }

    public int getMenge()
    {
        return menge;
    }

    public void setMenge(int menge)
    {
        this.menge = menge;
    }

    public double getPreis()
    {
        return preis;
    }

    public void setPreis(double preis)
    {
        this.preis = preis;
    }

    @Override
    public String toString()
    {
        return String.format("%s (%s) - %d stk - €%.2f", name, beschreibung, menge, preis);
    }
}
