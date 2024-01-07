package com.streams;

public class Transaction {
	
	private final Trader trader;
	private final int year;
	private final int value;
	private int price;
	private Currency currency;
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public Transaction(Trader trader, int year, int value) {
		super();
		this.trader = trader;
		this.year = year;
		this.value = value;
	}
	public Trader getTrader() {
		return trader;
	}
	public int getYear() {
		return year;
	}
	public int getValue() {
		return value;
	}
	
	
	public int getPrice() {
		return price;
	}
	public String toString(){
		return "{" + this.trader + ", " +
		"year: "+this.year+", " +
		"value:" + this.value +"}";
		}

}
