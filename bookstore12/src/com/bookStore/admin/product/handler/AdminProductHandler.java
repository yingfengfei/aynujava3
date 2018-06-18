package com.bookStore.admin.product.handler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bookStore.admin.product.service.IAdminProductService;
import com.bookStore.commons.beans.Product;
import com.bookStore.commons.beans.ProductList;
import com.bookStore.utils.IdUtils;

@Controller
@RequestMapping("/admin/products")
public class AdminProductHandler {
	
	@Autowired
	private IAdminProductService adminProductService;
	
	@RequestMapping("/listProduct.do")
	public String listProduct(Model model){
		List<Product> products = adminProductService.findProduct();
		
		model.addAttribute("products", products);
		return "/admin/products/list.jsp";
		
	}
	
	@RequestMapping("/findProductByManyCondition.do")
	public String findProductByManyCondition(Product product,String minprice,String maxprice,Model model){
		//System.out.println(product);
		//System.out.println(minprice+"-"+maxprice);
		List<Product> products = adminProductService.findProductByManyCondition(product,minprice,maxprice);
		model.addAttribute("products", products);
		model.addAttribute("product", product);
		model.addAttribute("minprice", minprice);
		model.addAttribute("maxprice", maxprice);
		
		return "/admin/products/list.jsp";
	}
	
	@RequestMapping("/addProduct.do")
	public String addProduct(Product product,MultipartFile upload,HttpSession session) throws IllegalStateException, IOException{
		//System.out.println(product);
		//System.out.println(upload);
		String path = session.getServletContext().getRealPath("/productImg");
		
		System.out.println(path);
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		String filename = IdUtils.getUUID() + "-" + upload.getOriginalFilename();
		String imgurl = path + File.separator + filename;
		upload.transferTo(new File(imgurl));
		
		product.setId(IdUtils.getUUID());
		product.setImgurl("/productImg/"+filename);
		
		adminProductService.addProduct(product);
		
		return "/admin/products/listProduct.do";
		
	}
	
	@RequestMapping("/findProductById.do")
	public String findProductById(String id,Model model){
		Product product = adminProductService.findProductById(id);
		model.addAttribute("p", product);
		return "/admin/products/edit.jsp";
	}
	
	@RequestMapping("/editProduct.do")
	public String editProduct(Product product,MultipartFile upload,HttpSession session) throws IllegalStateException, IOException{
		if(!upload.isEmpty()){
			String path = session.getServletContext().getRealPath("/productImg");
			Product target = adminProductService.findProductById(product.getId());
			File targetFile = new File(session.getServletContext().getRealPath("/") + target.getImgurl());
			
			//System.err.println(session.getServletContext().getRealPath("")+target.getImgurl());
			
			if(targetFile.exists()){
				targetFile.delete();
			}
			
			String fileName = IdUtils.getUUID() + "-" + upload.getOriginalFilename();
			upload.transferTo(new File(path + File.separator + fileName));
			product.setImgurl("/productImg/" + fileName);
		}
		adminProductService.editProduct(product);
		
		return "/admin/products/listProduct.do";
		
	}
	
	@RequestMapping("/deleteProduct.do")
	public String deleteProduct(String id,HttpSession session){
		Product target = adminProductService.findProductById(id);
		File targetFile = new File(session.getServletContext().getRealPath("/")+target.getImgurl());
		if(targetFile.exists()){
			targetFile.delete();
		}
		adminProductService.removeProduct(id);
		
		return "/admin/products/listProduct.do";
	}
	
	@RequestMapping("/download.do")
	public void download(String year,String month,HttpServletResponse response,
			HttpSession session,HttpServletRequest request) throws IOException{
		
		List<ProductList> plist = adminProductService.findProductList(year,month);
		
		String fileName = year + "年" + month + "月销售榜单";
		String sheetName = month + "月销售榜单";
		String titleName = year + "年" + month + "月销售榜单";
		
		String[] columnName = {"商品名称","销售数量"};
		int columnNumber = 2;
		
		String[][] dataList =  new String[plist.size()][2];
		
		for(int i=0;i<plist.size();i++){
			ProductList pl = plist.get(i);
			dataList[i][0] = pl.getName();
			dataList[i][1] = pl.getSalnum();
		}
		
		// 对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 创建sheet的第一行
		HSSFRow row1 = sheet.createRow(0);
		// 创建第一行的第一个列
		HSSFCell cell1 = row1.createCell(0);
		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNumber-1));
		// 给合并后的单元格赋值
		cell1.setCellValue(titleName);
		
		// 创建第二行
		HSSFRow row = sheet.createRow(1);
		for(int i=0;i<columnNumber;i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(columnName[i]);
		}
		
		// 创建数据行
		for(int i=0;i<dataList.length;i++){
			row = sheet.createRow(i + 2);
			HSSFCell datacell = null;
			for(int j=0;j<columnNumber;j++){
				datacell = row.createCell(j);
				datacell.setCellValue(dataList[i][j]);
			}
		}
		
		// 榜单下载
		String filename = fileName + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("content-Disposition", "attachment;filename="+processFileName(request, filename));
		
		OutputStream out = response.getOutputStream();
		wb.write(out);
		
		
		
		
		
		//导出csv文件
		/*String fileName = year + "年" + month + "月销售榜单.csv";
		response.setContentType(session.getServletContext().getMimeType(fileName));
		response.setHeader("content-Disposition", "attachment;filename="+processFileName(request, fileName));
		
		response.setCharacterEncoding("gbk");
		
		PrintWriter out = response.getWriter();
		out.println("商品名称,商品销量");
		for(int i=0;i<plist.size();i++){
			ProductList pl = plist.get(i);
			out.println(pl.getName() + "," + pl.getSalnum());
		}
		out.flush();
		out.close();*/
	
	}
	
	//IE、chrom、Firefox文件中文乱码问题
		public String processFileName(HttpServletRequest request, String fileNames) {  
		       String codedfilename = null;  
		       try {  
		           String agent = request.getHeader("USER-AGENT");  
		           if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
		                   && -1 != agent.indexOf("Trident")) {// ie  
		  
		               String name = java.net.URLEncoder.encode(fileNames, "UTF8");  
		  
		               codedfilename = name;  
		           } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等  
		  
		  
		               codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");  
		           }  
		       } catch (Exception e) {  
		           e.printStackTrace();  
		       }  
		       return codedfilename;  
		   }  
	
	
	
	
}
