import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}platformManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}platformManager/loadPlatform/${targetObjectId}/${parametersExpr}/`,
  })
}







const addProduct = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addProduct/platformId/name/introduction/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateProduct = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateProductProperties/platformId/id/name/introduction/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeProductList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeProductList/platformId/productIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addSkuInventory = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addSkuInventory/platformId/stockLevel/backorderLevel/preorderLevel/stockThreshold/backorderThreshol/preorderThreshol/status/productId/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateSkuInventory = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateSkuInventoryProperties/platformId/id/stockLevel/backorderLevel/preorderLevel/stockThreshold/backorderThreshol/preorderThreshol/status/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeSkuInventoryList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeSkuInventoryList/platformId/skuInventoryIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const PlatformService = { view,
  load,
  addProduct,
  addSkuInventory,
  updateProduct,
  updateSkuInventory,
  removeProductList,
  removeSkuInventoryList }
export default PlatformService

