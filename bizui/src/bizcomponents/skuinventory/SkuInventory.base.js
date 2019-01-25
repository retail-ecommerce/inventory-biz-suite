
import ImagePreview from '../../components/ImagePreview'
import { Link } from 'dva/router'
import moment from 'moment'
import appLocaleName from '../../common/Locale.tool'



const menuData = {menuName:"Sku Inventory", menuFor: "skuInventory",
  		subItems: [
  
  		],
}

const renderTextCell=(value, record)=>{
	const userContext = null
	if(!value){
		return '';
	}
	if(value==null){
		return '';
	}
	if(value.length>15){
		return value.substring(0,15)+"...("+value.length+appLocaleName(userContext,"Chars")+")"
	}
	return value
	
}

const renderIdentifier=(value, record, targtObjectType)=>{

	return (<Link to={`/${targtObjectType}/${value}/dashboard`}>{value}</Link>)
	
}

const renderDateCell=(value, record)=>{
	return moment(value).format('YYYY-MM-DD');
}
const renderDateTimeCell=(value, record)=>{
	return moment(value).format('YYYY-MM-DD HH:mm');	
}

const renderImageCell=(value, record, title)=>{
	return (<ImagePreview imageTitle={title} imageLocation={value} />)	
}

const renderMoneyCell=(value, record)=>{
	const userContext = null
	if(!value){
		return appLocaleName(userContext,"Empty")
	}
	if(value == null){
		return appLocaleName(userContext,"Empty")
	}
	return (`${appLocaleName(userContext,"Currency")}${value.toFixed(2)}`)
}

const renderBooleanCell=(value, record)=>{
	const userContext = null

	return  (value? appLocaleName(userContext,"Yes") : appLocaleName(userContext,"No"))

}

const renderReferenceCell=(value, record)=>{
	const userContext = null
	return (value ? value.displayName : appLocaleName(userContext,"NotAssigned")) 

}

const displayColumns = [
  { title: 'Id', debugtype: 'string', dataIndex: 'id', width: '20',render: (text, record)=>renderTextCell(text,record) },
  { title: 'Name', debugtype: 'string', dataIndex: 'name', width: '14',render: (text, record)=>renderTextCell(text,record) },
  { title: 'Stock Level', debugtype: 'int', dataIndex: 'stockLevel', width: '8',render: (text, record)=>renderTextCell(text,record) },
  { title: 'Backorder Level', debugtype: 'int', dataIndex: 'backorderLevel', width: '8',render: (text, record)=>renderTextCell(text,record) },
  { title: 'Preorder Level', debugtype: 'int', dataIndex: 'preorderLevel', width: '8',render: (text, record)=>renderTextCell(text,record) },
  { title: 'Stock Threshold', debugtype: 'int', dataIndex: 'stockThreshold', width: '8',render: (text, record)=>renderTextCell(text,record) },
  { title: 'Backorder Threshol', debugtype: 'int', dataIndex: 'backorderThreshol', width: '8',render: (text, record)=>renderTextCell(text,record) },
  { title: 'Preorder Threshol', debugtype: 'int', dataIndex: 'preorderThreshol', width: '8',render: (text, record)=>renderTextCell(text,record) },
  { title: 'Status', debugtype: 'string', dataIndex: 'status', width: '16',render: (text, record)=>renderTextCell(text,record) },
  { title: 'Product', dataIndex: 'product', render: (text, record) => renderReferenceCell(text, record)},
  { title: 'Platform', dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record)},

]

const fieldLabels = {
  id: 'Id',
  name: 'Name',
  stockLevel: 'Stock Level',
  backorderLevel: 'Backorder Level',
  preorderLevel: 'Preorder Level',
  stockThreshold: 'Stock Threshold',
  backorderThreshol: 'Backorder Threshol',
  preorderThreshol: 'Preorder Threshol',
  status: 'Status',
  product: 'Product',
  platform: 'Platform',

}


const SkuInventoryBase={menuData,displayColumns,fieldLabels}
export default SkuInventoryBase



