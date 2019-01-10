
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty skuInventory}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A SkuInventory" style="color: green">${userContext.localeMap['sku_inventory']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['sku_inventory.id']}</span> ${skuInventory.id}</li>
<li><span>${userContext.localeMap['sku_inventory.name']}</span> ${skuInventory.name}</li>
<li><span>${userContext.localeMap['sku_inventory.stock_level']}</span> ${skuInventory.stockLevel}</li>
<li><span>${userContext.localeMap['sku_inventory.backorder_level']}</span> ${skuInventory.backorderLevel}</li>
<li><span>${userContext.localeMap['sku_inventory.preorder_level']}</span> ${skuInventory.preorderLevel}</li>
<li><span>${userContext.localeMap['sku_inventory.stock_threshold']}</span> ${skuInventory.stockThreshold}</li>
<li><span>${userContext.localeMap['sku_inventory.backorder_threshol']}</span> ${skuInventory.backorderThreshol}</li>
<li><span>${userContext.localeMap['sku_inventory.preorder_threshol']}</span> ${skuInventory.preorderThreshol}</li>
<li><span>${userContext.localeMap['sku_inventory.status']}</span> ${skuInventory.status}</li>

	
	</ul>
</div>



</c:if>


