package id.hbbnas.getapi.network

import id.hbbnas.getapi.data.Agents
import id.hbbnas.getapi.data.DataItem
import id.hbbnas.getapi.data.Weapon
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("agents")
    fun getAllAgents() : Call<Agents>

    @GET("weapons")
    fun getAllWeapon() : Call<Weapon>
}