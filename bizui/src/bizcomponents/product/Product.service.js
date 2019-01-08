import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}productManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}productManager/loadProduct/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}productManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}productManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addSkuInventory = (targetObjectId, parameters) => {
  const url = `${PREFIX}productManager/addSkuInventory/productId/stockLevel/backorderLevel/preorderLevel/stockThreshold/backorderThreshol/preorderThreshol/status/platformId/tokensExpr/`
  const productId = targetObjectId
  const requestParameters = { ...parameters, productId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateSkuInventory = (targetObjectId, parameters) => {
  const url = `${PREFIX}productManager/updateSkuInventoryProperties/productId/id/stockLevel/backorderLevel/preorderLevel/stockThreshold/backorderThreshol/preorderThreshol/status/tokensExpr/`
  const productId = targetObjectId
  const requestParameters = { ...parameters, productId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeSkuInventoryList = (targetObjectId, parameters) => {
  const url = `${PREFIX}productManager/removeSkuInventoryList/productId/skuInventoryIds/tokensExpr/`
  const requestParameters = { ...parameters, productId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const ProductService = { view,
  load,
  addSkuInventory,
  updateSkuInventory,
  removeSkuInventoryList,
  requestCandidatePlatform,
  transferToAnotherPlatform }
export default ProductService

