//
//  ConversionViewController.swift
//  WorldTrotter
//
//  Created by 梓逸宸 on 2018/9/9.
//  Copyright © 2018 梓逸宸. All rights reserved.
//

import UIKit

class ConversionViewController: UIViewController{
    @IBOutlet var celsiusLabel: UILabel!
    @IBOutlet var textField: UITextField!
    
    var fahrenHeitValues: Double? {
        // 属性观察者，在属性改变之后执行
        didSet {
            updateCelsiusLabel()
        }
    }
    var celsiusValue: Double? {
        if let value = fahrenHeitValues {
            return (value - 32) * (5/9)
        }
        else{
            return nil
        }
    }
    
    func updateCelsiusLabel(){
        if let value = celsiusValue {
            let nsvalue = NSNumber(value: value)      //先将 double 类型的数据转化为 NSNumber 类型
            celsiusLabel.text = numberFormatter.string(from: nsvalue)
        }
        else{
            celsiusLabel.text = "???"
        }
    }
    
    @IBAction func fahrenHeitFieldEdittingChanged(textField: UITextField){
        // 根据填的值修改 fahrenHeitValues 的值
        if let text = textField.text, let value = Double(text){
            fahrenHeitValues = value
        }
        else{
            fahrenHeitValues = nil
        }
    }
    
    @IBAction func dissmissKeyBoard(sender: AnyObject){
        textField.resignFirstResponder()
    }
    
    // 数字格式化
    let numberFormatter: NumberFormatter = {
        let nf = NumberFormatter()
        nf.numberStyle = .decimal
        nf.minimumFractionDigits = 0
        nf.maximumFractionDigits = 1  // 最多只能有以为小数
        return nf
    }()
}
