//
//  ViewController.swift
//  Cube5
//
//  Created by 小波 on 2017/7/12.
//  Copyright © 2017年 xiaobo. All rights reserved.
//

import UIKit
import ARKit

class ViewController: UIViewController,SCNPhysicsContactDelegate {
    @IBOutlet weak var arscnView: ARSCNView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let scene = SCNScene()
        arscnView.scene = scene
        arscnView.showsStatistics = true
        
        //启动完成后添加飞船
        arscnView.addShip()
    }
    
    @IBAction func didTapScreen(_ sender: UITapGestureRecognizer) {
        //点击屏幕发射子弹
        arscnView.shootBullet()
        playSound(of: .torpedo)
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        let config = ARWorldTrackingConfiguration()
        config.planeDetection = .horizontal
        config.isLightEstimationEnabled = true
        arscnView.session.run(config)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func removeNodeDynamic(node: SCNNode, isExplode: Bool)  {
        playSound(of: .collision)
        
        if isExplode {
            playSound(of: .explosion)
            
            let ps = SCNParticleSystem(named: "explosion", inDirectory: nil)
            let psnode = SCNNode()
            psnode.addParticleSystem(ps!)
            
            psnode.position = node.position
            arscnView.scene.rootNode.addChildNode(psnode)
        }
        
        node.removeFromParentNode()
    }
    
    
    func physicsWorld(_ world: SCNPhysicsWorld, didBegin contact: SCNPhysicsContact) {
        print("击中")
        
        removeNodeDynamic(node: contact.nodeB, isExplode: false)
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            self.removeNodeDynamic(node: contact.nodeA, isExplode: true)
            self.arscnView.addShip()
        }
        
    }

}

