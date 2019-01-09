import React, { Component } from 'react'
import { Card, Button, Form, Icon, Col, Row, DatePicker, TimePicker, Input, Select, Popover,Switch,Modal } from 'antd'
import { connect } from 'dva'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import {ImageComponent} from '../../axios/tools'
import FooterToolbar from '../../components/FooterToolbar'
import styles from './SkuInventory.createform.less'
import {mapBackToImageValues, mapFromImageValues} from '../../axios/tools'
import GlobalComponents from '../../custcomponents';
import SkuInventoryBase from './SkuInventory.base'
import SelectObject from '../../components/SelectObject'


const { Option } = Select
const { RangePicker } = DatePicker
const { TextArea } = Input

const testValues = {};
/*
const testValues = {
  stockLevel: '710',
  backorderLevel: '740',
  preorderLevel: '899',
  stockThreshold: '805',
  backorderThreshol: '771',
  preorderThreshol: '712',
  status: 'IN_STOCK',
  productId: 'P000001',
  platformId: 'P000001',
}
*/


const imageKeys = [
]


class SkuInventoryAssociateForm extends Component {
  state = {
    previewVisible: false,
    previewImage: '',
    convertedImagesValues: {},
  }

  componentDidMount() {
 
    
    
    
  }

  handlePreview = (file) => {
    console.log('preview file', file)
    this.setState({
      previewImage: file.url || file.thumbUrl,
      previewVisible: true,
    })
  }

  



  handleChange = (event, source) => {
    console.log('get file list from change in update change:', source)

    const { fileList } = event
    const { convertedImagesValues } = this.state

    convertedImagesValues[source] = fileList
    this.setState({ convertedImagesValues })
    console.log('/get file list from change in update change:', source)
  }
	
  

  render() {
	const { form, dispatch, submitting, role,data,owner,toggleAssociatePaymentVisible,visible,onCancel, onCreate } = this.props
    const { convertedImagesValues } = this.state
    const {SkuInventoryService} = GlobalComponents


    const { getFieldDecorator, validateFieldsAndScroll, getFieldsError } = form
    const {fieldLabels} = SkuInventoryBase
    
    const capFirstChar = (value)=>{
    	//const upper = value.replace(/^\w/, c => c.toUpperCase());
  		const upper = value.charAt(0).toUpperCase() + value.substr(1);
  		return upper
  	}
    
    
    

    
    
    const tryinit  = (fieldName, candidates) => {
      
      if(candidates&&candidates.length==1){
          return candidates[0].id
      }
      const { owner } = this.props
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return null
      }
      return owner.id
    }
    
    const availableForEdit= (fieldName) =>{
      const { owner } = this.props
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return true
      }
      return false
    
    }
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 12 },
    }
    const switchFormItemLayout = {
      labelCol: { span: 14 },
      wrapperCol: { span: 4 },
    }
    
    return (
 <Modal
          title="创建新的支付"
          visible={visible}
          onOk={onCancel}
          onCancel={onCancel}
          width={920}
          style={{ top: 40}}
        >
        <Card title="基础信息"  className={styles.card} style={{ backgroundColor:"#eee" }}>
          <Form >
            <Row gutter={16}>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.stockLevel} {...formItemLayout}>
                  {getFieldDecorator('stockLevel', {
                    rules: [{ required: true, message: '请输入Stock Level' }],
                  })(
                    <Input placeholder="请输入Stock Level" />
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.backorderLevel} {...formItemLayout}>
                  {getFieldDecorator('backorderLevel', {
                    rules: [{ required: true, message: '请输入Backorder Level' }],
                  })(
                    <Input placeholder="请输入Backorder Level" />
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.preorderLevel} {...formItemLayout}>
                  {getFieldDecorator('preorderLevel', {
                    rules: [{ required: true, message: '请输入Preorder Level' }],
                  })(
                    <Input placeholder="请输入Preorder Level" />
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.stockThreshold} {...formItemLayout}>
                  {getFieldDecorator('stockThreshold', {
                    rules: [{ required: true, message: '请输入Stock Threshold' }],
                  })(
                    <Input placeholder="请输入Stock Threshold" />
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.backorderThreshol} {...formItemLayout}>
                  {getFieldDecorator('backorderThreshol', {
                    rules: [{ required: true, message: '请输入Backorder Threshol' }],
                  })(
                    <Input placeholder="请输入Backorder Threshol" />
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.preorderThreshol} {...formItemLayout}>
                  {getFieldDecorator('preorderThreshol', {
                    rules: [{ required: true, message: '请输入Preorder Threshol' }],
                  })(
                    <Input placeholder="请输入Preorder Threshol" />
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.status} {...formItemLayout}>
                  {getFieldDecorator('status', {
                    rules: [{ required: true, message: '请输入Status' }],
                  })(
                    <Input placeholder="请输入Status" />
                  )}
                </Form.Item>
              </Col>

            </Row>


       
        









       
            <Row gutter={16}>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.product} {...formItemLayout}>
                  {getFieldDecorator('productId', {
                  	initialValue: tryinit('product'),
                    rules: [{ required: true, message: '请输入Product' }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('product')}
                    targetType={"product"} 
                    requestFunction={SkuInventoryService.requestCandidateProduct}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.platform} {...formItemLayout}>
                  {getFieldDecorator('platformId', {
                  	initialValue: tryinit('platform'),
                    rules: [{ required: true, message: '请输入Platform' }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('platform')}
                    targetType={"platform"} 
                    requestFunction={SkuInventoryService.requestCandidatePlatform}/>
  
                  )}
                </Form.Item>
              </Col>

            </Row>
         
       

			</Form>
			
			
			
			
        </Card>
        
        
        
        
      </Modal>)
    
  }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
}))(Form.create()(SkuInventoryAssociateForm))




