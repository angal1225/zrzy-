package com.formwork.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("创建session："+arg0.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("销毁session："+arg0.getSession().getId());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("新增属性：" + arg0.getName() + ":"+arg0.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("删除属性：" + arg0.getName() + ":"+arg0.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("重置属性：" + arg0.getName() + ":"+arg0.getValue());
	}
}
