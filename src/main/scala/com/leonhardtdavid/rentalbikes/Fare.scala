package com.leonhardtdavid.rentalbikes

sealed trait Fare {
  def price: BigDecimal
}

case class FarePerHour() extends Fare {
  val price: BigDecimal = 5
}

case class FarePerDay() extends Fare {
  val price: BigDecimal = 20
}

case class FarePerWeek() extends Fare {
  val price: BigDecimal = 60
}

case class FarePerFamily(qty: Int, fare: Fare) extends Fare {

  require(qty >= 3 && qty <= 5, "qty for a family fare needs to be between 3 and 5")
  require(!fare.isInstanceOf[FarePerFamily], "you can't use a FarePerFamily as a fare for another FarePerFamily")

  val price: BigDecimal = (this.qty * this.fare.price) * 0.7 // 0.7 represents a 30% discount

}
