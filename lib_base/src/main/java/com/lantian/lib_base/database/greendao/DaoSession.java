package com.lantian.lib_base.database.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lantian.lib_base.entity.module.response.farmer.farmmsg.FarmMsgResponse;
import com.lantian.lib_base.entity.module.response.farmer.plan.countPlantsum;
import com.lantian.lib_base.entity.module.response.farmer.plan.CaoyuanList;
import com.lantian.lib_base.entity.module.response.farmer.farmdatas.FarmInCome;
import com.lantian.lib_base.entity.module.response.farmer.farmdatas.FarmSumData;
import com.lantian.lib_base.entity.module.response.farmer.butie.ButieList;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.FarmListResponse;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.PersonList;
import com.lantian.lib_base.entity.module.response.farmer.farmhuku.CheckUser;
import com.lantian.lib_base.entity.module.response.farmer.farmhuku.HukuFind;
import com.lantian.lib_base.entity.module.response.product.ProductResponse;
import com.lantian.lib_base.entity.module.response.img.Imgs;
import com.lantian.lib_base.entity.module.response.breeds.AddBreed;
import com.lantian.lib_base.entity.module.response.breeds.addEarTag;
import com.lantian.lib_base.entity.module.response.breeds.BreedFind;
import com.lantian.lib_base.entity.module.response.breeds.Breeds;
import com.lantian.lib_base.entity.module.response.breeds.Pinzhong;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;
import com.lantian.lib_base.entity.module.response.breeds.InOut;
import com.lantian.lib_base.entity.module.response.breeds.BreedsOfUser;
import com.lantian.lib_base.entity.module.response.breeds.CountBreedTure;
import com.lantian.lib_base.entity.module.response.breeds.BreedClassFind;
import com.lantian.lib_base.entity.module.response.breeds.CountCompensate;
import com.lantian.lib_base.entity.module.response.breeds.EarTag;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.entity.module.response.breeds.CountBreeddatas;
import com.lantian.lib_base.entity.module.response.breeds.editEarTag;
import com.lantian.lib_base.entity.module.response.breeds.Laiyuan;
import com.lantian.lib_base.entity.module.response.adress.Adress;
import com.lantian.lib_base.entity.module.response.userinfo.UserInfo;
import com.lantian.lib_base.entity.module.response.login.LoginResponse;
import com.lantian.lib_base.entity.items.Devs;

import com.lantian.lib_base.database.greendao.FarmMsgResponseDao;
import com.lantian.lib_base.database.greendao.countPlantsumDao;
import com.lantian.lib_base.database.greendao.CaoyuanListDao;
import com.lantian.lib_base.database.greendao.FarmInComeDao;
import com.lantian.lib_base.database.greendao.FarmSumDataDao;
import com.lantian.lib_base.database.greendao.ButieListDao;
import com.lantian.lib_base.database.greendao.HuzhuListDao;
import com.lantian.lib_base.database.greendao.FarmListResponseDao;
import com.lantian.lib_base.database.greendao.PersonListDao;
import com.lantian.lib_base.database.greendao.CheckUserDao;
import com.lantian.lib_base.database.greendao.HukuFindDao;
import com.lantian.lib_base.database.greendao.ProductResponseDao;
import com.lantian.lib_base.database.greendao.ImgsDao;
import com.lantian.lib_base.database.greendao.AddBreedDao;
import com.lantian.lib_base.database.greendao.addEarTagDao;
import com.lantian.lib_base.database.greendao.BreedFindDao;
import com.lantian.lib_base.database.greendao.BreedsDao;
import com.lantian.lib_base.database.greendao.PinzhongDao;
import com.lantian.lib_base.database.greendao.BreedsListDao;
import com.lantian.lib_base.database.greendao.InOutDao;
import com.lantian.lib_base.database.greendao.BreedsOfUserDao;
import com.lantian.lib_base.database.greendao.CountBreedTureDao;
import com.lantian.lib_base.database.greendao.BreedClassFindDao;
import com.lantian.lib_base.database.greendao.CountCompensateDao;
import com.lantian.lib_base.database.greendao.EarTagDao;
import com.lantian.lib_base.database.greendao.BreedIndexDao;
import com.lantian.lib_base.database.greendao.CountBreeddatasDao;
import com.lantian.lib_base.database.greendao.editEarTagDao;
import com.lantian.lib_base.database.greendao.LaiyuanDao;
import com.lantian.lib_base.database.greendao.AdressDao;
import com.lantian.lib_base.database.greendao.UserInfoDao;
import com.lantian.lib_base.database.greendao.LoginResponseDao;
import com.lantian.lib_base.database.greendao.DevsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig farmMsgResponseDaoConfig;
    private final DaoConfig countPlantsumDaoConfig;
    private final DaoConfig caoyuanListDaoConfig;
    private final DaoConfig farmInComeDaoConfig;
    private final DaoConfig farmSumDataDaoConfig;
    private final DaoConfig butieListDaoConfig;
    private final DaoConfig huzhuListDaoConfig;
    private final DaoConfig farmListResponseDaoConfig;
    private final DaoConfig personListDaoConfig;
    private final DaoConfig checkUserDaoConfig;
    private final DaoConfig hukuFindDaoConfig;
    private final DaoConfig productResponseDaoConfig;
    private final DaoConfig imgsDaoConfig;
    private final DaoConfig addBreedDaoConfig;
    private final DaoConfig addEarTagDaoConfig;
    private final DaoConfig breedFindDaoConfig;
    private final DaoConfig breedsDaoConfig;
    private final DaoConfig pinzhongDaoConfig;
    private final DaoConfig breedsListDaoConfig;
    private final DaoConfig inOutDaoConfig;
    private final DaoConfig breedsOfUserDaoConfig;
    private final DaoConfig countBreedTureDaoConfig;
    private final DaoConfig breedClassFindDaoConfig;
    private final DaoConfig countCompensateDaoConfig;
    private final DaoConfig earTagDaoConfig;
    private final DaoConfig breedIndexDaoConfig;
    private final DaoConfig countBreeddatasDaoConfig;
    private final DaoConfig editEarTagDaoConfig;
    private final DaoConfig laiyuanDaoConfig;
    private final DaoConfig adressDaoConfig;
    private final DaoConfig userInfoDaoConfig;
    private final DaoConfig loginResponseDaoConfig;
    private final DaoConfig devsDaoConfig;

    private final FarmMsgResponseDao farmMsgResponseDao;
    private final countPlantsumDao countPlantsumDao;
    private final CaoyuanListDao caoyuanListDao;
    private final FarmInComeDao farmInComeDao;
    private final FarmSumDataDao farmSumDataDao;
    private final ButieListDao butieListDao;
    private final HuzhuListDao huzhuListDao;
    private final FarmListResponseDao farmListResponseDao;
    private final PersonListDao personListDao;
    private final CheckUserDao checkUserDao;
    private final HukuFindDao hukuFindDao;
    private final ProductResponseDao productResponseDao;
    private final ImgsDao imgsDao;
    private final AddBreedDao addBreedDao;
    private final addEarTagDao addEarTagDao;
    private final BreedFindDao breedFindDao;
    private final BreedsDao breedsDao;
    private final PinzhongDao pinzhongDao;
    private final BreedsListDao breedsListDao;
    private final InOutDao inOutDao;
    private final BreedsOfUserDao breedsOfUserDao;
    private final CountBreedTureDao countBreedTureDao;
    private final BreedClassFindDao breedClassFindDao;
    private final CountCompensateDao countCompensateDao;
    private final EarTagDao earTagDao;
    private final BreedIndexDao breedIndexDao;
    private final CountBreeddatasDao countBreeddatasDao;
    private final editEarTagDao editEarTagDao;
    private final LaiyuanDao laiyuanDao;
    private final AdressDao adressDao;
    private final UserInfoDao userInfoDao;
    private final LoginResponseDao loginResponseDao;
    private final DevsDao devsDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        farmMsgResponseDaoConfig = daoConfigMap.get(FarmMsgResponseDao.class).clone();
        farmMsgResponseDaoConfig.initIdentityScope(type);

        countPlantsumDaoConfig = daoConfigMap.get(countPlantsumDao.class).clone();
        countPlantsumDaoConfig.initIdentityScope(type);

        caoyuanListDaoConfig = daoConfigMap.get(CaoyuanListDao.class).clone();
        caoyuanListDaoConfig.initIdentityScope(type);

        farmInComeDaoConfig = daoConfigMap.get(FarmInComeDao.class).clone();
        farmInComeDaoConfig.initIdentityScope(type);

        farmSumDataDaoConfig = daoConfigMap.get(FarmSumDataDao.class).clone();
        farmSumDataDaoConfig.initIdentityScope(type);

        butieListDaoConfig = daoConfigMap.get(ButieListDao.class).clone();
        butieListDaoConfig.initIdentityScope(type);

        huzhuListDaoConfig = daoConfigMap.get(HuzhuListDao.class).clone();
        huzhuListDaoConfig.initIdentityScope(type);

        farmListResponseDaoConfig = daoConfigMap.get(FarmListResponseDao.class).clone();
        farmListResponseDaoConfig.initIdentityScope(type);

        personListDaoConfig = daoConfigMap.get(PersonListDao.class).clone();
        personListDaoConfig.initIdentityScope(type);

        checkUserDaoConfig = daoConfigMap.get(CheckUserDao.class).clone();
        checkUserDaoConfig.initIdentityScope(type);

        hukuFindDaoConfig = daoConfigMap.get(HukuFindDao.class).clone();
        hukuFindDaoConfig.initIdentityScope(type);

        productResponseDaoConfig = daoConfigMap.get(ProductResponseDao.class).clone();
        productResponseDaoConfig.initIdentityScope(type);

        imgsDaoConfig = daoConfigMap.get(ImgsDao.class).clone();
        imgsDaoConfig.initIdentityScope(type);

        addBreedDaoConfig = daoConfigMap.get(AddBreedDao.class).clone();
        addBreedDaoConfig.initIdentityScope(type);

        addEarTagDaoConfig = daoConfigMap.get(addEarTagDao.class).clone();
        addEarTagDaoConfig.initIdentityScope(type);

        breedFindDaoConfig = daoConfigMap.get(BreedFindDao.class).clone();
        breedFindDaoConfig.initIdentityScope(type);

        breedsDaoConfig = daoConfigMap.get(BreedsDao.class).clone();
        breedsDaoConfig.initIdentityScope(type);

        pinzhongDaoConfig = daoConfigMap.get(PinzhongDao.class).clone();
        pinzhongDaoConfig.initIdentityScope(type);

        breedsListDaoConfig = daoConfigMap.get(BreedsListDao.class).clone();
        breedsListDaoConfig.initIdentityScope(type);

        inOutDaoConfig = daoConfigMap.get(InOutDao.class).clone();
        inOutDaoConfig.initIdentityScope(type);

        breedsOfUserDaoConfig = daoConfigMap.get(BreedsOfUserDao.class).clone();
        breedsOfUserDaoConfig.initIdentityScope(type);

        countBreedTureDaoConfig = daoConfigMap.get(CountBreedTureDao.class).clone();
        countBreedTureDaoConfig.initIdentityScope(type);

        breedClassFindDaoConfig = daoConfigMap.get(BreedClassFindDao.class).clone();
        breedClassFindDaoConfig.initIdentityScope(type);

        countCompensateDaoConfig = daoConfigMap.get(CountCompensateDao.class).clone();
        countCompensateDaoConfig.initIdentityScope(type);

        earTagDaoConfig = daoConfigMap.get(EarTagDao.class).clone();
        earTagDaoConfig.initIdentityScope(type);

        breedIndexDaoConfig = daoConfigMap.get(BreedIndexDao.class).clone();
        breedIndexDaoConfig.initIdentityScope(type);

        countBreeddatasDaoConfig = daoConfigMap.get(CountBreeddatasDao.class).clone();
        countBreeddatasDaoConfig.initIdentityScope(type);

        editEarTagDaoConfig = daoConfigMap.get(editEarTagDao.class).clone();
        editEarTagDaoConfig.initIdentityScope(type);

        laiyuanDaoConfig = daoConfigMap.get(LaiyuanDao.class).clone();
        laiyuanDaoConfig.initIdentityScope(type);

        adressDaoConfig = daoConfigMap.get(AdressDao.class).clone();
        adressDaoConfig.initIdentityScope(type);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        loginResponseDaoConfig = daoConfigMap.get(LoginResponseDao.class).clone();
        loginResponseDaoConfig.initIdentityScope(type);

        devsDaoConfig = daoConfigMap.get(DevsDao.class).clone();
        devsDaoConfig.initIdentityScope(type);

        farmMsgResponseDao = new FarmMsgResponseDao(farmMsgResponseDaoConfig, this);
        countPlantsumDao = new countPlantsumDao(countPlantsumDaoConfig, this);
        caoyuanListDao = new CaoyuanListDao(caoyuanListDaoConfig, this);
        farmInComeDao = new FarmInComeDao(farmInComeDaoConfig, this);
        farmSumDataDao = new FarmSumDataDao(farmSumDataDaoConfig, this);
        butieListDao = new ButieListDao(butieListDaoConfig, this);
        huzhuListDao = new HuzhuListDao(huzhuListDaoConfig, this);
        farmListResponseDao = new FarmListResponseDao(farmListResponseDaoConfig, this);
        personListDao = new PersonListDao(personListDaoConfig, this);
        checkUserDao = new CheckUserDao(checkUserDaoConfig, this);
        hukuFindDao = new HukuFindDao(hukuFindDaoConfig, this);
        productResponseDao = new ProductResponseDao(productResponseDaoConfig, this);
        imgsDao = new ImgsDao(imgsDaoConfig, this);
        addBreedDao = new AddBreedDao(addBreedDaoConfig, this);
        addEarTagDao = new addEarTagDao(addEarTagDaoConfig, this);
        breedFindDao = new BreedFindDao(breedFindDaoConfig, this);
        breedsDao = new BreedsDao(breedsDaoConfig, this);
        pinzhongDao = new PinzhongDao(pinzhongDaoConfig, this);
        breedsListDao = new BreedsListDao(breedsListDaoConfig, this);
        inOutDao = new InOutDao(inOutDaoConfig, this);
        breedsOfUserDao = new BreedsOfUserDao(breedsOfUserDaoConfig, this);
        countBreedTureDao = new CountBreedTureDao(countBreedTureDaoConfig, this);
        breedClassFindDao = new BreedClassFindDao(breedClassFindDaoConfig, this);
        countCompensateDao = new CountCompensateDao(countCompensateDaoConfig, this);
        earTagDao = new EarTagDao(earTagDaoConfig, this);
        breedIndexDao = new BreedIndexDao(breedIndexDaoConfig, this);
        countBreeddatasDao = new CountBreeddatasDao(countBreeddatasDaoConfig, this);
        editEarTagDao = new editEarTagDao(editEarTagDaoConfig, this);
        laiyuanDao = new LaiyuanDao(laiyuanDaoConfig, this);
        adressDao = new AdressDao(adressDaoConfig, this);
        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);
        loginResponseDao = new LoginResponseDao(loginResponseDaoConfig, this);
        devsDao = new DevsDao(devsDaoConfig, this);

        registerDao(FarmMsgResponse.class, farmMsgResponseDao);
        registerDao(countPlantsum.class, countPlantsumDao);
        registerDao(CaoyuanList.class, caoyuanListDao);
        registerDao(FarmInCome.class, farmInComeDao);
        registerDao(FarmSumData.class, farmSumDataDao);
        registerDao(ButieList.class, butieListDao);
        registerDao(HuzhuList.class, huzhuListDao);
        registerDao(FarmListResponse.class, farmListResponseDao);
        registerDao(PersonList.class, personListDao);
        registerDao(CheckUser.class, checkUserDao);
        registerDao(HukuFind.class, hukuFindDao);
        registerDao(ProductResponse.class, productResponseDao);
        registerDao(Imgs.class, imgsDao);
        registerDao(AddBreed.class, addBreedDao);
        registerDao(addEarTag.class, addEarTagDao);
        registerDao(BreedFind.class, breedFindDao);
        registerDao(Breeds.class, breedsDao);
        registerDao(Pinzhong.class, pinzhongDao);
        registerDao(BreedsList.class, breedsListDao);
        registerDao(InOut.class, inOutDao);
        registerDao(BreedsOfUser.class, breedsOfUserDao);
        registerDao(CountBreedTure.class, countBreedTureDao);
        registerDao(BreedClassFind.class, breedClassFindDao);
        registerDao(CountCompensate.class, countCompensateDao);
        registerDao(EarTag.class, earTagDao);
        registerDao(BreedIndex.class, breedIndexDao);
        registerDao(CountBreeddatas.class, countBreeddatasDao);
        registerDao(editEarTag.class, editEarTagDao);
        registerDao(Laiyuan.class, laiyuanDao);
        registerDao(Adress.class, adressDao);
        registerDao(UserInfo.class, userInfoDao);
        registerDao(LoginResponse.class, loginResponseDao);
        registerDao(Devs.class, devsDao);
    }
    
    public void clear() {
        farmMsgResponseDaoConfig.clearIdentityScope();
        countPlantsumDaoConfig.clearIdentityScope();
        caoyuanListDaoConfig.clearIdentityScope();
        farmInComeDaoConfig.clearIdentityScope();
        farmSumDataDaoConfig.clearIdentityScope();
        butieListDaoConfig.clearIdentityScope();
        huzhuListDaoConfig.clearIdentityScope();
        farmListResponseDaoConfig.clearIdentityScope();
        personListDaoConfig.clearIdentityScope();
        checkUserDaoConfig.clearIdentityScope();
        hukuFindDaoConfig.clearIdentityScope();
        productResponseDaoConfig.clearIdentityScope();
        imgsDaoConfig.clearIdentityScope();
        addBreedDaoConfig.clearIdentityScope();
        addEarTagDaoConfig.clearIdentityScope();
        breedFindDaoConfig.clearIdentityScope();
        breedsDaoConfig.clearIdentityScope();
        pinzhongDaoConfig.clearIdentityScope();
        breedsListDaoConfig.clearIdentityScope();
        inOutDaoConfig.clearIdentityScope();
        breedsOfUserDaoConfig.clearIdentityScope();
        countBreedTureDaoConfig.clearIdentityScope();
        breedClassFindDaoConfig.clearIdentityScope();
        countCompensateDaoConfig.clearIdentityScope();
        earTagDaoConfig.clearIdentityScope();
        breedIndexDaoConfig.clearIdentityScope();
        countBreeddatasDaoConfig.clearIdentityScope();
        editEarTagDaoConfig.clearIdentityScope();
        laiyuanDaoConfig.clearIdentityScope();
        adressDaoConfig.clearIdentityScope();
        userInfoDaoConfig.clearIdentityScope();
        loginResponseDaoConfig.clearIdentityScope();
        devsDaoConfig.clearIdentityScope();
    }

    public FarmMsgResponseDao getFarmMsgResponseDao() {
        return farmMsgResponseDao;
    }

    public countPlantsumDao getCountPlantsumDao() {
        return countPlantsumDao;
    }

    public CaoyuanListDao getCaoyuanListDao() {
        return caoyuanListDao;
    }

    public FarmInComeDao getFarmInComeDao() {
        return farmInComeDao;
    }

    public FarmSumDataDao getFarmSumDataDao() {
        return farmSumDataDao;
    }

    public ButieListDao getButieListDao() {
        return butieListDao;
    }

    public HuzhuListDao getHuzhuListDao() {
        return huzhuListDao;
    }

    public FarmListResponseDao getFarmListResponseDao() {
        return farmListResponseDao;
    }

    public PersonListDao getPersonListDao() {
        return personListDao;
    }

    public CheckUserDao getCheckUserDao() {
        return checkUserDao;
    }

    public HukuFindDao getHukuFindDao() {
        return hukuFindDao;
    }

    public ProductResponseDao getProductResponseDao() {
        return productResponseDao;
    }

    public ImgsDao getImgsDao() {
        return imgsDao;
    }

    public AddBreedDao getAddBreedDao() {
        return addBreedDao;
    }

    public addEarTagDao getAddEarTagDao() {
        return addEarTagDao;
    }

    public BreedFindDao getBreedFindDao() {
        return breedFindDao;
    }

    public BreedsDao getBreedsDao() {
        return breedsDao;
    }

    public PinzhongDao getPinzhongDao() {
        return pinzhongDao;
    }

    public BreedsListDao getBreedsListDao() {
        return breedsListDao;
    }

    public InOutDao getInOutDao() {
        return inOutDao;
    }

    public BreedsOfUserDao getBreedsOfUserDao() {
        return breedsOfUserDao;
    }

    public CountBreedTureDao getCountBreedTureDao() {
        return countBreedTureDao;
    }

    public BreedClassFindDao getBreedClassFindDao() {
        return breedClassFindDao;
    }

    public CountCompensateDao getCountCompensateDao() {
        return countCompensateDao;
    }

    public EarTagDao getEarTagDao() {
        return earTagDao;
    }

    public BreedIndexDao getBreedIndexDao() {
        return breedIndexDao;
    }

    public CountBreeddatasDao getCountBreeddatasDao() {
        return countBreeddatasDao;
    }

    public editEarTagDao getEditEarTagDao() {
        return editEarTagDao;
    }

    public LaiyuanDao getLaiyuanDao() {
        return laiyuanDao;
    }

    public AdressDao getAdressDao() {
        return adressDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public LoginResponseDao getLoginResponseDao() {
        return loginResponseDao;
    }

    public DevsDao getDevsDao() {
        return devsDao;
    }

}
