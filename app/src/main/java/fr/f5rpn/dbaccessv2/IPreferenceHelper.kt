package fr.f5rpn.dbaccessv2

interface IPreferenceHelper {
    fun setServer(server: String)
    fun getServer(): String
    fun setSynchro(synchro: String)
    fun getSynchro(): String
    fun clearPrefs()

}