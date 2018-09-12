//
//  MapViewController.swift
//  WorldTrotter
//
//  Created by 梓逸宸 on 2018/9/12.
//  Copyright © 2018 梓逸宸. All rights reserved.
//

import UIKit
import MapKit

class MapViewController: UIViewController {
    var mapview: MKMapView!
    
    override func loadView() {
        // 创建一个地图视图
        mapview = MKMapView()
        
        //设置其为当前视图的视图控制器
        view = mapview
        
        let segmentedControl = UISegmentedControl(items: ["Standard", "Hybrid", "Satellite"])
        segmentedControl.backgroundColor = UIColor.white.withAlphaComponent(0.5)
        segmentedControl.selectedSegmentIndex = 0
        
        segmentedControl.addTarget(self,
                    action: #selector(MapViewController.mapTypeChanged(_:)),
                    for: .valueChanged)
        
        // 动态拉伸视图尺寸---关闭
        segmentedControl.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(segmentedControl)
        
        //添加锚点约束
        // 设置上边距
        let topConstraint = segmentedControl.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 8)
        // 设置左右边距
        let margins = view.layoutMarginsGuide
        let leadingConstraint = segmentedControl.leadingAnchor.constraint(equalTo: margins.leadingAnchor)
        let trailingConstraint = segmentedControl.trailingAnchor.constraint(equalTo: margins.trailingAnchor)
        
        topConstraint.isActive = true
        leadingConstraint.isActive = true
        trailingConstraint.isActive = true
        
    }
    
    @objc func mapTypeChanged(_ segControl: UISegmentedControl){
        switch segControl.selectedSegmentIndex {
        case 0:
            mapview.mapType = .standard
        case 1:
            mapview.mapType = .hybrid
        case 2:
            mapview.mapType = .satellite
        default:
            break
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("MapViewController loaded its view.")
    }
    
    
}
