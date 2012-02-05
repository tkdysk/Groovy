BigDecimal num = args ? new BigDecimal(args[0]) : 10
BigDecimal p = a = 0.5
num.times{ p += (a *= (2*(it+1)-1)*(2*(it+1)-1)/(8*(it+1)*(2*(it+1)+1)))}
println 6*p