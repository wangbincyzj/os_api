package tech.wangbin.base.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

public class PagerUtil {
  @Value("pager.defaultSize")
  private static final int defaultSize = 10;

  public static <T> IPage<T> parse(HttpServletRequest req, Class<T> t) {
    int page, size;
    Object reqPage, reqSize;
    String method = req.getMethod();
    if ("GET".equals(method)) {
      reqPage = req.getParameter("page");
      reqSize = req.getParameter("size");
    } else {
      reqPage = req.getAttribute("page");
      reqSize = req.getAttribute("size");
    }

    if (null == reqPage) {
      return null;
    } else {
      if (reqPage instanceof String) {
        page = Integer.parseInt((String) reqPage);
      } else {
        page = (int) reqPage;
      }
      if (null == reqSize) {
        size = defaultSize;
      } else if (reqSize instanceof String) {
        size = Integer.parseInt((String) reqSize);
      } else {
        size = (int) reqSize;
      }
    }
    return new Page<>(page, size);
  }
}