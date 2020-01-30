module setExamples

sig Fruit{}

sig Banana, Apple, Pear extends Fruit{}

sig Fresh, Expensive in Fruit {}

pred show{}
run show for 5



pred bargainApple {

    some Apple & (Fresh - Expensive)
}
run bargainApple



pred noNastyBananas{

	 no Banana & (Expensive - Fresh)
}
run noNastyBananas



pred allFreshFruitIsExpensive {
	
	//Expensive in(Fruit & Fresh)
 	//no Fruit & (Fresh - Expensive)
	Fresh in Fruit &(Expensive)
}
run allFreshFruitIsExpensive



pred BananasAndFreshApplesAreExpensive {

	some (Apple & (Fresh & Expensive)) && some Banana & (Expensive)
}
run BananasAndFreshApplesAreExpensive








pred notFreshBananas {

	some Fruit no(Fresh & Banana)
}
run notFreshBananas



pred notFreshAndExpensive {

	some Fruit no(Fresh & Expensive)
}
run notFreshAndExpensive









pred notFruitAndExpensive {

	some Expensive no(Fruit)
}
run notFruitAndExpensive


pred BananasAndApplesAreFresh {

	Banana + Apple in Fresh
}
run BananasAndApplesAreFresh










