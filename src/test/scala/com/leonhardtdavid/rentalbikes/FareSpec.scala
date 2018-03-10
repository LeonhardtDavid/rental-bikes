package com.leonhardtdavid.rentalbikes

import org.scalatest.mockito.MockitoSugar
import org.scalatest.{ FlatSpec, Matchers }

import scala.util.Random

class FareSpec extends FlatSpec with Matchers with MockitoSugar {

  "A FarePerHour" should "have 5 as price" in {
    FarePerHour().price should be(5)
  }

  "A FarePerDay" should "have 20 as price" in {
    FarePerDay().price should be(20)
  }

  "A FarePerWeek" should "have 60 as price" in {
    FarePerWeek().price should be(60)
  }

  "A FarePerFamily" should "fail if qty is less than 3" in {
    assertThrows[IllegalArgumentException] {
      FarePerFamily(Random.nextInt(3), mock[Fare])
    }
  }

  it should "fail if qty is greater than 5" in {
    assertThrows[IllegalArgumentException] {
      FarePerFamily(Random.nextInt() + 6, mock[Fare])
    }
  }

  it should "fail if fare is another FarePerFamily" in {
    assertThrows[IllegalArgumentException] {
      FarePerFamily(4, mock[FarePerFamily])
    }
  }

  it should "have a 30% discount with FarePerHour" in {
    val qty = Random.nextInt(3) + 3
    val expectedPrice = (qty: BigDecimal) * 3.5

    val fare = FarePerFamily(qty, FarePerHour())

    fare.price should be(expectedPrice)
  }

  it should "have a 30% discount with FarePerDay" in {
    val qty = Random.nextInt(3) + 3
    val expectedPrice = (qty: BigDecimal) * 14

    val fare = FarePerFamily(qty, FarePerDay())

    fare.price should be(expectedPrice)
  }

  it should "have a 30% discount with FarePerWeek" in {
    val qty = Random.nextInt(3) + 3
    val expectedPrice = (qty: BigDecimal) * 42

    val fare = FarePerFamily(qty, FarePerWeek())

    fare.price should be(expectedPrice)
  }

}
