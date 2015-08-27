package net.chrisrichardson.customersandorders.commondomain.valueobjects;

import java.math.BigDecimal;

public class Money {

  public static final Money ZERO = new Money(new BigDecimal(0));
  private BigDecimal amount;

  public Money() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Money money = (Money) o;

    if (!amount.equals(money.amount)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return amount.hashCode();
  }

  public Money(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Money subtract(Money total) {
    return new Money(amount.subtract(total.amount));
  }

  public boolean isLessThanOrEqualTo(Money money) {
    return amount.compareTo(money.amount) <= 0;
  }

  public Money add(Money orderTotal) {
    return new Money(amount.add(orderTotal.amount));
  }

  public String asString() {
    return amount.toPlainString();
  }
}
