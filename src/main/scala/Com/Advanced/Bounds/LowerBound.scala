package Com.Advanced.Bounds

object LowerBound extends App{
  val egypt = new Egypt
  val wholeCairoCityDistricts = new CairoCity
  val NasrCityDistrict = new NasrCityDistrict
  val cairoDistricts = new CairoDistricts
  cairoDistricts.districts(egypt)
  cairoDistricts.districts(wholeCairoCityDistricts)
  cairoDistricts.districts(NasrCityDistrict)


}

class Egypt[-T]

class CairoCity extends Egypt[String]

class NasrCityDistrict extends Egypt[String]

class CairoDistricts{
  //Here T is a Type Parameter ans S is a type.
  // By declaring Upper Bound like “[T <: S]” means this Type Parameter
  // T must be either same as S (CairoCity) or Sub-Type of S.
  def districts [T>:CairoCity](t:T): Unit ={
    println(t)
  }
}
