package com.epitech.billy.omega_splicer.domain.interactors;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This interface is a boundary between the setting presenter and the interactor
 * <p/>
 */
public interface IGetCurrentSettingsInteractor extends IBaseInteractor {
    String getCurrentControlSetting();
    String getCurrentSpeedUnitSetting();
}
