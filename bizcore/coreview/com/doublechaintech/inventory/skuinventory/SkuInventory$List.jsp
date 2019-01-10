<%@ page import='java.util.*,com.doublechaintech.inventory.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty skuInventoryList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['sku_inventory']}! 
		 <a href="./${ownerBeanName}Manager/addSkuInventory/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty skuInventoryList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("skuInventoryList"); 
 	int totalCount = list.getTotalCount();
 	List pages = list.getPages();
 	pageContext.setAttribute("rowsPerPage",list.getRowsPerPage()); 
 	
 	pageContext.setAttribute("pages",pages); 
 	pageContext.setAttribute("totalCount",totalCount); 
 	//pageContext.setAttribute("accessible",list.isAccessible()); 
 	//the reason using scriptlet here is the el express is currently not able resolv common property from a subclass of list
%>
    
 	   
    <div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		<i class='fa fa-table'></i> ${userContext.localeMap['sku_inventory']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addSkuInventory/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'skuInventoryList' eq action.actionGroup}">
		<a class="btn btn-${action.actionLevel} btn-sm" href="${action.managerBeanName}/${action.actionPath}">${userContext.localeMap[action.localeKey]}</a>
		</c:if>
	</c:forEach>
	</div><!--end of div class="btn-group" -->
	
		 
		 
		 
		 </div>
 </div>
    
    
<div class="table-responsive">


<c:set var="currentPageNumber" value="1"/>	



<nav aria-label="Page navigation example">
  <ul class="pagination">
<c:forEach var="page" items="${pages}"> 
<c:set var="classType" value=""/>
<c:if test='${page.selected}' >
<c:set var="classType" value="active"/>
<c:set var="currentPageNumber" value="${page.pageNumber}"/>
</c:if>


	<li class="page-item ${classType}">
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${skuInventoryListName};${skuInventoryListName}CurrentPage=${page.pageNumber};${skuInventoryListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='skuInventoryListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['sku_inventory.id']}</th>
</c:if>
<c:if test="${param.referName ne 'name'}">
	<th>${userContext.localeMap['sku_inventory.name']}</th>
</c:if>
<c:if test="${param.referName ne 'stockLevel'}">
	<th>${userContext.localeMap['sku_inventory.stock_level']}</th>
</c:if>
<c:if test="${param.referName ne 'backorderLevel'}">
	<th>${userContext.localeMap['sku_inventory.backorder_level']}</th>
</c:if>
<c:if test="${param.referName ne 'preorderLevel'}">
	<th>${userContext.localeMap['sku_inventory.preorder_level']}</th>
</c:if>
<c:if test="${param.referName ne 'stockThreshold'}">
	<th>${userContext.localeMap['sku_inventory.stock_threshold']}</th>
</c:if>
<c:if test="${param.referName ne 'backorderThreshol'}">
	<th>${userContext.localeMap['sku_inventory.backorder_threshol']}</th>
</c:if>
<c:if test="${param.referName ne 'preorderThreshol'}">
	<th>${userContext.localeMap['sku_inventory.preorder_threshol']}</th>
</c:if>
<c:if test="${param.referName ne 'status'}">
	<th>${userContext.localeMap['sku_inventory.status']}</th>
</c:if>
<c:if test="${param.referName ne 'product'}">
	<th>${userContext.localeMap['sku_inventory.product']}</th>
</c:if>
<c:if test="${param.referName ne 'platform'}">
	<th>${userContext.localeMap['sku_inventory.platform']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${skuInventoryList}">
				<tr currentVersion='${item.version}' id="skuInventory-${item.id}" ><td><a class="link-action-removed" href="./skuInventoryManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'name'}">	<td contenteditable='true' class='edit-value'  propertyToChange='name' storedCellValue='${item.name}' prefix='${ownerBeanName}Manager/updateSkuInventory/${result.id}/${item.id}/'>${item.name}</td>
</c:if><c:if test="${param.referName ne 'stockLevel'}">	<td contenteditable='true' class='edit-value'  propertyToChange='stockLevel' storedCellValue='${item.stockLevel}' prefix='${ownerBeanName}Manager/updateSkuInventory/${result.id}/${item.id}/'>${item.stockLevel}</td>
</c:if><c:if test="${param.referName ne 'backorderLevel'}">	<td contenteditable='true' class='edit-value'  propertyToChange='backorderLevel' storedCellValue='${item.backorderLevel}' prefix='${ownerBeanName}Manager/updateSkuInventory/${result.id}/${item.id}/'>${item.backorderLevel}</td>
</c:if><c:if test="${param.referName ne 'preorderLevel'}">	<td contenteditable='true' class='edit-value'  propertyToChange='preorderLevel' storedCellValue='${item.preorderLevel}' prefix='${ownerBeanName}Manager/updateSkuInventory/${result.id}/${item.id}/'>${item.preorderLevel}</td>
</c:if><c:if test="${param.referName ne 'stockThreshold'}">	<td contenteditable='true' class='edit-value'  propertyToChange='stockThreshold' storedCellValue='${item.stockThreshold}' prefix='${ownerBeanName}Manager/updateSkuInventory/${result.id}/${item.id}/'>${item.stockThreshold}</td>
</c:if><c:if test="${param.referName ne 'backorderThreshol'}">	<td contenteditable='true' class='edit-value'  propertyToChange='backorderThreshol' storedCellValue='${item.backorderThreshol}' prefix='${ownerBeanName}Manager/updateSkuInventory/${result.id}/${item.id}/'>${item.backorderThreshol}</td>
</c:if><c:if test="${param.referName ne 'preorderThreshol'}">	<td contenteditable='true' class='edit-value'  propertyToChange='preorderThreshol' storedCellValue='${item.preorderThreshol}' prefix='${ownerBeanName}Manager/updateSkuInventory/${result.id}/${item.id}/'>${item.preorderThreshol}</td>
</c:if><c:if test="${param.referName ne 'status'}">	<td contenteditable='true' class='edit-value'  propertyToChange='status' storedCellValue='${item.status}' prefix='${ownerBeanName}Manager/updateSkuInventory/${result.id}/${item.id}/'>${item.status}</td>
</c:if><c:if test="${param.referName ne 'product'}">
	<td class="select_candidate_td"
			data-candidate-method="./skuInventoryManager/requestCandidateProduct/${ownerBeanName}/${item.id}/"
			data-switch-method="./skuInventoryManager/transferToAnotherProduct/${item.id}/"
			data-link-template="./productManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.product}">
			<a href='./productManager/view/${item.product.id}/'>${item.product.displayName}</a>
			</c:if>
			<c:if test="${empty  item.product}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'platform'}">
	<td class="select_candidate_td"
			data-candidate-method="./skuInventoryManager/requestCandidatePlatform/${ownerBeanName}/${item.id}/"
			data-switch-method="./skuInventoryManager/transferToAnotherPlatform/${item.id}/"
			data-link-template="./platformManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.platform}">
			<a href='./platformManager/view/${item.platform.id}/'>${item.platform.displayName}</a>
			</c:if>
			<c:if test="${empty  item.platform}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>

				<td>

				<a href='#${ownerBeanName}Manager/removeSkuInventory/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copySkuInventoryFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


