/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.knowledgebase.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.service.KBArticleLocalService;
import com.liferay.knowledgebase.service.KBArticleService;
import com.liferay.knowledgebase.service.KBCommentLocalService;
import com.liferay.knowledgebase.service.KBTemplateLocalService;
import com.liferay.knowledgebase.service.KBTemplateService;
import com.liferay.knowledgebase.service.persistence.KBArticlePersistence;
import com.liferay.knowledgebase.service.persistence.KBCommentPersistence;
import com.liferay.knowledgebase.service.persistence.KBTemplatePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.social.service.SocialActivityLocalService;
import com.liferay.portlet.social.service.persistence.SocialActivityPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the k b comment local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.knowledgebase.service.impl.KBCommentLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledgebase.service.impl.KBCommentLocalServiceImpl
 * @see com.liferay.knowledgebase.service.KBCommentLocalServiceUtil
 * @generated
 */
public abstract class KBCommentLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements KBCommentLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.knowledgebase.service.KBCommentLocalServiceUtil} to access the k b comment local service.
	 */

	/**
	 * Adds the k b comment to the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbComment the k b comment
	 * @return the k b comment that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public KBComment addKBComment(KBComment kbComment)
		throws SystemException {
		kbComment.setNew(true);

		return kbCommentPersistence.update(kbComment, false);
	}

	/**
	 * Creates a new k b comment with the primary key. Does not add the k b comment to the database.
	 *
	 * @param kbCommentId the primary key for the new k b comment
	 * @return the new k b comment
	 */
	public KBComment createKBComment(long kbCommentId) {
		return kbCommentPersistence.create(kbCommentId);
	}

	/**
	 * Deletes the k b comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbCommentId the primary key of the k b comment
	 * @return the k b comment that was removed
	 * @throws PortalException if a k b comment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public KBComment deleteKBComment(long kbCommentId)
		throws PortalException, SystemException {
		return kbCommentPersistence.remove(kbCommentId);
	}

	/**
	 * Deletes the k b comment from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbComment the k b comment
	 * @return the k b comment that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public KBComment deleteKBComment(KBComment kbComment)
		throws SystemException {
		return kbCommentPersistence.remove(kbComment);
	}

	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(KBComment.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return kbCommentPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return kbCommentPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return kbCommentPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return kbCommentPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public KBComment fetchKBComment(long kbCommentId) throws SystemException {
		return kbCommentPersistence.fetchByPrimaryKey(kbCommentId);
	}

	/**
	 * Returns the k b comment with the primary key.
	 *
	 * @param kbCommentId the primary key of the k b comment
	 * @return the k b comment
	 * @throws PortalException if a k b comment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBComment getKBComment(long kbCommentId)
		throws PortalException, SystemException {
		return kbCommentPersistence.findByPrimaryKey(kbCommentId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return kbCommentPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the k b comment with the UUID in the group.
	 *
	 * @param uuid the UUID of k b comment
	 * @param groupId the group id of the k b comment
	 * @return the k b comment
	 * @throws PortalException if a k b comment with the UUID in the group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KBComment getKBCommentByUuidAndGroupId(String uuid, long groupId)
		throws PortalException, SystemException {
		return kbCommentPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the k b comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b comments
	 * @param end the upper bound of the range of k b comments (not inclusive)
	 * @return the range of k b comments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KBComment> getKBComments(int start, int end)
		throws SystemException {
		return kbCommentPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of k b comments.
	 *
	 * @return the number of k b comments
	 * @throws SystemException if a system exception occurred
	 */
	public int getKBCommentsCount() throws SystemException {
		return kbCommentPersistence.countAll();
	}

	/**
	 * Updates the k b comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param kbComment the k b comment
	 * @return the k b comment that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public KBComment updateKBComment(KBComment kbComment)
		throws SystemException {
		return updateKBComment(kbComment, true);
	}

	/**
	 * Updates the k b comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param kbComment the k b comment
	 * @param merge whether to merge the k b comment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the k b comment that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public KBComment updateKBComment(KBComment kbComment, boolean merge)
		throws SystemException {
		kbComment.setNew(false);

		return kbCommentPersistence.update(kbComment, merge);
	}

	/**
	 * Returns the k b article local service.
	 *
	 * @return the k b article local service
	 */
	public KBArticleLocalService getKBArticleLocalService() {
		return kbArticleLocalService;
	}

	/**
	 * Sets the k b article local service.
	 *
	 * @param kbArticleLocalService the k b article local service
	 */
	public void setKBArticleLocalService(
		KBArticleLocalService kbArticleLocalService) {
		this.kbArticleLocalService = kbArticleLocalService;
	}

	/**
	 * Returns the k b article remote service.
	 *
	 * @return the k b article remote service
	 */
	public KBArticleService getKBArticleService() {
		return kbArticleService;
	}

	/**
	 * Sets the k b article remote service.
	 *
	 * @param kbArticleService the k b article remote service
	 */
	public void setKBArticleService(KBArticleService kbArticleService) {
		this.kbArticleService = kbArticleService;
	}

	/**
	 * Returns the k b article persistence.
	 *
	 * @return the k b article persistence
	 */
	public KBArticlePersistence getKBArticlePersistence() {
		return kbArticlePersistence;
	}

	/**
	 * Sets the k b article persistence.
	 *
	 * @param kbArticlePersistence the k b article persistence
	 */
	public void setKBArticlePersistence(
		KBArticlePersistence kbArticlePersistence) {
		this.kbArticlePersistence = kbArticlePersistence;
	}

	/**
	 * Returns the k b comment local service.
	 *
	 * @return the k b comment local service
	 */
	public KBCommentLocalService getKBCommentLocalService() {
		return kbCommentLocalService;
	}

	/**
	 * Sets the k b comment local service.
	 *
	 * @param kbCommentLocalService the k b comment local service
	 */
	public void setKBCommentLocalService(
		KBCommentLocalService kbCommentLocalService) {
		this.kbCommentLocalService = kbCommentLocalService;
	}

	/**
	 * Returns the k b comment persistence.
	 *
	 * @return the k b comment persistence
	 */
	public KBCommentPersistence getKBCommentPersistence() {
		return kbCommentPersistence;
	}

	/**
	 * Sets the k b comment persistence.
	 *
	 * @param kbCommentPersistence the k b comment persistence
	 */
	public void setKBCommentPersistence(
		KBCommentPersistence kbCommentPersistence) {
		this.kbCommentPersistence = kbCommentPersistence;
	}

	/**
	 * Returns the k b template local service.
	 *
	 * @return the k b template local service
	 */
	public KBTemplateLocalService getKBTemplateLocalService() {
		return kbTemplateLocalService;
	}

	/**
	 * Sets the k b template local service.
	 *
	 * @param kbTemplateLocalService the k b template local service
	 */
	public void setKBTemplateLocalService(
		KBTemplateLocalService kbTemplateLocalService) {
		this.kbTemplateLocalService = kbTemplateLocalService;
	}

	/**
	 * Returns the k b template remote service.
	 *
	 * @return the k b template remote service
	 */
	public KBTemplateService getKBTemplateService() {
		return kbTemplateService;
	}

	/**
	 * Sets the k b template remote service.
	 *
	 * @param kbTemplateService the k b template remote service
	 */
	public void setKBTemplateService(KBTemplateService kbTemplateService) {
		this.kbTemplateService = kbTemplateService;
	}

	/**
	 * Returns the k b template persistence.
	 *
	 * @return the k b template persistence
	 */
	public KBTemplatePersistence getKBTemplatePersistence() {
		return kbTemplatePersistence;
	}

	/**
	 * Sets the k b template persistence.
	 *
	 * @param kbTemplatePersistence the k b template persistence
	 */
	public void setKBTemplatePersistence(
		KBTemplatePersistence kbTemplatePersistence) {
		this.kbTemplatePersistence = kbTemplatePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the social activity local service.
	 *
	 * @return the social activity local service
	 */
	public SocialActivityLocalService getSocialActivityLocalService() {
		return socialActivityLocalService;
	}

	/**
	 * Sets the social activity local service.
	 *
	 * @param socialActivityLocalService the social activity local service
	 */
	public void setSocialActivityLocalService(
		SocialActivityLocalService socialActivityLocalService) {
		this.socialActivityLocalService = socialActivityLocalService;
	}

	/**
	 * Returns the social activity persistence.
	 *
	 * @return the social activity persistence
	 */
	public SocialActivityPersistence getSocialActivityPersistence() {
		return socialActivityPersistence;
	}

	/**
	 * Sets the social activity persistence.
	 *
	 * @param socialActivityPersistence the social activity persistence
	 */
	public void setSocialActivityPersistence(
		SocialActivityPersistence socialActivityPersistence) {
		this.socialActivityPersistence = socialActivityPersistence;
	}

	public void afterPropertiesSet() {
		PersistedModelLocalServiceRegistryUtil.register("com.liferay.knowledgebase.model.KBComment",
			kbCommentLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.knowledgebase.model.KBComment");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
	}

	protected Class<?> getModelClass() {
		return KBComment.class;
	}

	protected String getModelClassName() {
		return KBComment.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = kbCommentPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = KBArticleLocalService.class)
	protected KBArticleLocalService kbArticleLocalService;
	@BeanReference(type = KBArticleService.class)
	protected KBArticleService kbArticleService;
	@BeanReference(type = KBArticlePersistence.class)
	protected KBArticlePersistence kbArticlePersistence;
	@BeanReference(type = KBCommentLocalService.class)
	protected KBCommentLocalService kbCommentLocalService;
	@BeanReference(type = KBCommentPersistence.class)
	protected KBCommentPersistence kbCommentPersistence;
	@BeanReference(type = KBTemplateLocalService.class)
	protected KBTemplateLocalService kbTemplateLocalService;
	@BeanReference(type = KBTemplateService.class)
	protected KBTemplateService kbTemplateService;
	@BeanReference(type = KBTemplatePersistence.class)
	protected KBTemplatePersistence kbTemplatePersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = SocialActivityLocalService.class)
	protected SocialActivityLocalService socialActivityLocalService;
	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	private String _beanIdentifier;
	private KBCommentLocalServiceClpInvoker _clpInvoker = new KBCommentLocalServiceClpInvoker();
}