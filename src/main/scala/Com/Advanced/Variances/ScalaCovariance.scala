package Com.Advanced.Variances

object ScalaCovariance extends App {
  val nasrCity = new NasrCityDistrict

  val cairoCity = new CairoCity

  val egyptNasrCity: Egypt[NasrCityDistrict] = new Egypt[NasrCityDistrict](nasrCity)

  val egyptCairoCity: Egypt[CairoCity] = new Egypt[CairoCity](cairoCity)

  val wholeCairoDistricts = new CairoDistricts(egyptCairoCity)

  val cairoNasrCityDistrict = new CairoDistricts(egyptNasrCity)
  

}

class Egypt[+T](val cityDistricts: T)

class CairoCity

class NasrCityDistrict extends CairoCity

class CairoDistricts(val cairoCity: Egypt[CairoCity])