package my.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.github.pagehelper.Page;

/**
 * 对象复制工具类。
 * 
 * @author wuzhaobin
 *
 */
public class BeanUtil {
	
	/**
	 * 将数据复制为目标对象列表。
	 * 
	 * @param source 源对象列表
	 * @param clazz  目标对象类型
	 * @return  目标对象列表
	 */
	public static <S, T> List<T> copy(List<S> source, Class<T> clazz) {
		if (source == null) {
			return null;
		}
		
		List<T> target = null;
		@SuppressWarnings("unchecked")
        Class<? extends List<T>> listClazz = (Class<? extends List<T>>) source.getClass();
		try {
            target = listClazz.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e1) {
            // 如果不能通过class来实例化，则使用ArrayList.
        }
		if (target == null) {
		    target = new ArrayList<T>(source.size());
		}
		
		for (S sourceItem : source) {
			try {
				T targetItem = clazz.newInstance();
				BeanUtils.copyProperties(sourceItem, targetItem);
				target.add(targetItem);
			} catch (Exception e) {
				throw new BaseException(e);
			}
		}
		
		return target;
	}
	
	/**
	 * 将源对象数据复制为目标对象。
	 * 
	 * @param sourceItem 源对象数据
	 * @param clazz  目标对象类型
	 * @return 目标对象数据
	 */
	public static <S, T> T copy(S sourceItem, Class<T> clazz) {
        if (sourceItem == null) {
            return null;
        }
        
		try {
			T targetItem = clazz.newInstance();
			BeanUtils.copyProperties(sourceItem, targetItem);
			return targetItem;
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}
	
	/**
	 * 将list转换为Page对象。（兼容普通List）
	 * 
	 * @param list
	 * @return
	 */
	public static <T> PageVo<T> page(List<T> list) {
	    PageVo<T> pageVo = new PageVo<T>();
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            pageVo.setPageNum(page.getPageNum());
            pageVo.setPageSize(page.getPageSize());
            pageVo.setPages(page.getPages());
            pageVo.setData(page);
            pageVo.setSize(page.size());
            pageVo.setTotal(page.getTotal());
            //由于结果是>startRow的，所以实际的需要+1
            if (pageVo.getSize() == 0) {
                pageVo.setStartRow(0);
                pageVo.setEndRow(0);
            } else {
                pageVo.setStartRow(page.getStartRow() + 1);
                //计算实际的endRow（最后一页的时候特殊）
                pageVo.setEndRow(pageVo.getStartRow() - 1 + pageVo.getSize());
            }
        } else if (list instanceof Collection) {
            pageVo.setPageNum(1);
            pageVo.setPageSize(list.size());
            pageVo.setPages(pageVo.getPageSize() > 0 ? 1 : 0);
            pageVo.setData(list);
            pageVo.setSize(list.size());
            pageVo.setTotal(list.size());
            pageVo.setStartRow(0);
            pageVo.setEndRow(list.size() > 0 ? list.size() - 1 : 0);
        }
	    return pageVo;
	}

}
