package Com.Advanced.Bounds

object UpperBound extends App{
val egypt = new Egyptz
val wholeCairoCityDistricts = new CairoCityz
val NasrCityDistrict = new NasrCityDistrictz
val cairoDistricts = new CairoDistrictsz
// cairoDistricts.districts(egypt) compile error restricted access due to upper bound
cairoDistricts.districts(wholeCairoCityDistricts)
cairoDistricts.districts(NasrCityDistrict)


}
class Egyptz[+T]

class CairoCityz extends Egyptz[String]

class NasrCityDistrictz extends CairoCityz

class CairoDistrictsz{
  //Here T is a Type Parameter ans S is a type.
  // By declaring Upper Bound like “[T <: S]” means this Type Parameter
  // T must be either same as S (CairoCity) or Sub-Type of S.
  def districts [T<:CairoCityz](t:T): Unit ={
    println(t)
  }
}