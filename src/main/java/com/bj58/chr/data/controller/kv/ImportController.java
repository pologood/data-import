package com.bj58.chr.data.controller.kv;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.MenuType;
import com.bj58.chr.common.UUIDUtils;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.common.poi.impl.ExcellHandlerImpl;
import com.bj58.chr.common.poi.model.ExcelEntity;
import com.bj58.chr.common.poi.model.Result;
import com.bj58.chr.data.controller.SuperController;
import com.bj58.chr.data.handler.ImportHandler;
import com.bj58.chr.data.handler.factory.ImportEntityFactory;
import com.bj58.chr.data.model.XlsUpload;
import com.bj58.chr.data.service.IKVEntityService;
import com.bj58.chr.data.service.IKVGroupService;
import com.bj58.chr.data.xls.Area;
import com.bj58.chr.data.xls.CompanyType;
import com.bj58.chr.data.xls.Degrees;
import com.bj58.chr.data.xls.Industry;
import com.bj58.chr.data.xls.Job;
import com.bj58.chr.data.xls.Language;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月14日 下午12:28:56
 * @see
 * @since
 */

@Controller
@MenuType(name = "字典表管理", index = 2)
@RequestMapping("/xls")
public class ImportController extends SuperController {

	@Resource
	private IKVGroupService kVGroupService;
	
	@Resource
	private IKVEntityService kVEntityService;
	
	@Resource(name="importEntityFactory")
	private ImportEntityFactory factory;
	
	@RequestMapping(value = "/upload")
	@MenuType(name = "工作信息导入")
	public ModelAndView upload(@ModelAttribute XlsUpload xlsUpload, BindingResult result, HttpServletRequest request) {
		xlsUpload.setGroup(kVGroupService.findById(xlsUpload.getGroup().getId()));
		if (WebUtils.isPOST(request)) {
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			validate(xlsUpload, result);
			MultipartFile xls = req.getFile("xls");
			xlsUpload.setXls(saveFile(xls));
			importData(xlsUpload, false);
			return forward("xls/result").addObject("xlsUpload", xlsUpload).addObject("result",
					importData(xlsUpload, false));
		}
		return forward("xls/upload");
	}

	public String saveFile(MultipartFile file) {
		String uuid = UUIDUtils.getUUID();
		String folder = System.getProperty("java.io.tmpdir");
		String realPath = new StringBuilder(200).append(folder).append(uuid).append(DOT)
				.append(FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase()).toString();
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath));
		} catch (IOException e) {
			realPath = StringUtils.EMPTY;
		}
		return realPath;
	}

	@RequestMapping(value = "/importData")
	@MenuType(name = "确认导入")
	public ModelAndView importData(@ModelAttribute XlsUpload xlsUpload, BindingResult result,
			HttpServletRequest request) {
		importData(xlsUpload, true);
		return forward("xls/import");
	}

    private Result<?> importData(XlsUpload xlsUpload, boolean save) {
	    switch (xlsUpload.getGroup().getId()) {
            case 15:
                return importData(xlsUpload, save, Job.class, factory.getImportFactory(Job.class));
            case 17:
                return importData(xlsUpload, save, Industry.class, factory.getImportFactory(Industry.class));
            case 9:
                return importData(xlsUpload, save, Degrees.class, factory.getImportFactory(Degrees.class));
            case 11:
                return importData(xlsUpload, save, Area.class, factory.getImportFactory(Area.class));
            case 13:
                return importData(xlsUpload, save, Language.class, factory.getImportFactory(Language.class));
            case 14:
                return importData(xlsUpload, save, CompanyType.class, factory.getImportFactory(CompanyType.class));
            default:
                return null;
        }

	}

	private <T> Result<T> importData(XlsUpload xlsUpload, boolean save, Class<T> cls, ImportHandler<T> handler) {
		ExcellHandlerImpl<T> ehi = new ExcellHandlerImpl<>();
		ExcelEntity<T> ee = new ExcelEntity<T>(cls);
		Result<T> jobResult = ehi.skipFirstLine().setSheet(xlsUpload.getSheet())
				.importExcell(new File(xlsUpload.getXls()), ee);
		if (save) {
			for (T t : jobResult.getEntitys()) {
				handler.execute(xlsUpload.getGroup(), t);
			}
		}
		return jobResult;
	}

}
