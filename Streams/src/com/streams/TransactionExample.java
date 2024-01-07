package com.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionExample {

	public static void main(String[] args) {

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		Stream<Transaction> transactionStream = transactions.stream();

		Set<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity())
				.collect(Collectors.toSet());

		String traderStr = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct()
				.sorted().collect(Collectors.joining());
		System.out.println(traderStr);
		
		
		Optional<Transaction> smallestTransaction0 = transactions.stream().
				reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
		Optional<Transaction> smallestTransaction = transactions.stream().min(Comparator.comparing(Transaction::getValue));

		Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
				.filter((Transaction t) -> t.getPrice() > 1000)
				.collect(Collectors.groupingBy(Transaction::getCurrency));

		List<Transaction> transactions1 = transactionStream.collect(Collectors.toList());
		
		String traderStr1 = 
				transactions.stream()
				.map(transaction -> transaction.getTrader().getName())
				.distinct()
				.sorted()
				.reduce("", (n1,n2) -> n1 + n2);
		
		boolean milanBased = 
				transactions.stream()
				.anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
		
		transactions.stream()
		.filter(t -> "Cambridge".equals(t.getTrader().getCity()))
		.map(Transaction::getValue)
		.forEach(System.out::println);
		
		Optional<Integer> highestValue =
				transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::max);
		
		Optional<Transaction> mostExpensive =
				transactions.stream()
				.max(Comparator.comparing(Transaction::getValue));
	}

}
