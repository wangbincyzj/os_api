package tech.wangbin.base.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import tech.wangbin.base.support.BaseController;
import tech.wangbin.base.support.BaseEntity;
import tech.wangbin.base.support.Resp;

import java.util.*;

public class Gen {
  // 包位置配置
  static final String PackageName = "tech.wangbin";
  static final String ModuleName = "domain";

  // 作者
  static final String Author = "WangBin";

  // 支撑类
  static Class<?> controller = BaseController.class;
  static Class<?> response = Resp.class;
  static Class<?> entity = BaseEntity.class;

  // 数据库配置
  static final String DataSourceUrl = "jdbc:mysql://47.95.192.129:3306/os_api?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
  static final String DataSourceDriverClassName = "com.mysql.cj.jdbc.Driver";
  static final String DataSourceUsername = "wangbin";
  static final String DataSourcePassword = "1234567dyy";

  public static void main(String[] args) {
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");
    gc.setOutputDir(projectPath + "/src/main/java");
    gc.setAuthor(Author);
    gc.setOpen(false);
    // gc.setSwagger2(true); 实体属性 Swagger2 注解
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(DataSourceUrl);
    dsc.setDriverName(DataSourceDriverClassName);
    dsc.setUsername(DataSourceUsername);
    dsc.setPassword(DataSourcePassword);
    mpg.setDataSource(dsc);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setModuleName(ModuleName);
    pc.setParent(PackageName);
    mpg.setPackageInfo(pc);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("controllerPackage", controller.getPackage().getName());
        map.put("controllerName", controller.getName());
        map.put("responsePackage", response.getPackage().getName());
        map.put("responseName", response.getName());
        map.put("entityPath", entity.getPackage().getName());
        this.setMap(map);
      }
    };

    // 如果模板引擎是 freemarker
    String templatePath = "/templates/mapper.xml.ftl";// 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<>();
    // 自定义配置会被优先输出
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
          + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
      }
    });
    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);
    // 配置模板
    TemplateConfig templateConfig = new TemplateConfig();
    templateConfig.setEntity("templates/entity.java");
    templateConfig.setService("templates/service.java");
    templateConfig.setXml("templates/serviceImpl.java");
    templateConfig.setController("templates/controller.java");
    templateConfig.setXml(null);
    mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setSuperEntityClass(entity);
    strategy.setEntityLombokModel(true);
    strategy.setRestControllerStyle(true);
    strategy.setLogicDeleteFieldName("is_delete");
    strategy.setSuperControllerClass(controller);

    // 写于父类中的公共字段
    // strategy.setSuperEntityColumns("id");
    strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setTablePrefix(pc.getModuleName() + "_");
    mpg.setStrategy(strategy);
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    mpg.execute();
  }

  public static String scanner(String tip) {
    Scanner scanner = new Scanner(System.in);
    StringBuilder help = new StringBuilder();
    help.append("请输入" + tip + "：");
    System.out.println(help.toString());
    if (scanner.hasNext()) {
      String ipt = scanner.next();
      if (StringUtils.isNotBlank(ipt)) {
        return ipt;
      }
    }
    throw new MybatisPlusException("请输入正确的" + tip + "！");
  }
}
