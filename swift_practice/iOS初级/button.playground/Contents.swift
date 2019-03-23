//: Playground - noun: a place where people can play

import UIKit

var 矩形 = CGRect(x: 0, y: 0, width: 200, height: 40)
var button = UILabel(frame: 矩形)

button.text = "立即订购"
button.textColor = UIColor.white
button.backgroundColor = UIColor.purple
button.textAlignment = .center
button.layer.cornerRadius = 10
button.clipsToBounds = true
