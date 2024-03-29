

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from 'components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'
import {
  ChartCard, yuan, MiniArea, MiniBar, MiniProgress, Field, Bar, Pie, TimelineChart,
} from '../../components/Charts'
import Trend from '../../components/Trend'
import NumberInfo from '../../components/NumberInfo'
import { getTimeDistance } from '../../utils/utils'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './SkuInventory.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(skuInventory)=>{return [
	 ]}

const internalImageListOf = (skuInventory) =>defaultImageListOf(skuInventory,imageList)

const optionList =(skuInventory)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalSettingListOf = (skuInventory) =>defaultSettingListOf(skuInventory, optionList)
const internalLargeTextOf = (skuInventory) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (skuInventory,targetComponent) =>{
	
	
	const {SkuInventoryService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="Id">{skuInventory.id}</Description> 
<Description term="Name">{skuInventory.name}</Description> 
<Description term="Stock Level">{skuInventory.stockLevel}</Description> 
<Description term="Backorder Level">{skuInventory.backorderLevel}</Description> 
<Description term="Preorder Level">{skuInventory.preorderLevel}</Description> 
<Description term="Stock Threshold">{skuInventory.stockThreshold}</Description> 
<Description term="Backorder Threshol">{skuInventory.backorderThreshol}</Description> 
<Description term="Preorder Threshol">{skuInventory.preorderThreshol}</Description> 
<Description term="Status">{skuInventory.status}</Description> 
<Description term="Product">{skuInventory.product==null?appLocaleName(userContext,"NotAssigned"):skuInventory.product.displayName}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Product","product",SkuInventoryService.requestCandidateProduct,
	      SkuInventoryService.transferToAnotherProduct,"anotherProductId",skuInventory.product?skuInventory.product.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(skuInventory,targetComponent)}
      </DescriptionList>
	)

}


class SkuInventoryDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'skuInventory'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName,  } = this.props.skuInventory
    if(!this.props.skuInventory.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"Sku Inventory",cardsFor: "skuInventory",
    	cardsSource: this.props.skuInventory,returnURL,displayName,
  		subItems: [
    
      	],
  	};
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const settingListOf = this.props.settingListOf || internalSettingListOf
    const imageListOf = this.props.imageListOf || internalImageListOf
    const subListsOf = this.props.subListsOf || internalSubListsOf
    const largeTextOf = this.props.largeTextOf ||internalLargeTextOf
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderTitle = this.props.renderTitle || internalRenderTitle
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
        <div>
        {settingListOf(cardsData.cardsSource)}
        {imageListOf(cardsData.cardsSource)}
        {subListsOf(cardsData)} 
        {largeTextOf(cardsData.cardsSource)}
          
        </div>
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  skuInventory: state._skuInventory,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(SkuInventoryDashboard))

