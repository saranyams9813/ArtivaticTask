package com.example.artivaticTask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artivaticTask.R
import com.example.artivaticTask.adapter.FeedsListAdapter
import com.example.artivaticTask.data.viewmodel.HomeListViewmodel
import com.example.artivaticTask.databinding.ActivityHomeListBinding
import com.example.artivaticTask.utils.ExceptionHandling.getErrorMessage
import java.lang.Exception

class HomeListActivity : AppCompatActivity() {
   var binding : ActivityHomeListBinding?=null
    var viewmodel: HomeListViewmodel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_home_list)
        binding?.setLifecycleOwner(this)
        viewmodel = ViewModelProvider(this).get(HomeListViewmodel::class.java)
        setUpUI()
        setUpClickListeners()
        setUpObserver()
    }



    private fun setUpUI() {
        binding?.feedRv?.layoutManager=LinearLayoutManager(this)
        binding?.feedRv?.adapter = FeedsListAdapter(ArrayList())
    }
    private fun setUpClickListeners() {
        binding?.apply {
            retryBtn?.setOnClickListener{
                setUpObserver()
            }
            pullToRefresh?.setOnRefreshListener {
                pullToRefresh.isRefreshing=true
                setUpObserver()

            }
        }
    }

    private fun setUpObserver() {
        binding?.progressBarCyclic?.visibility= if(!(binding?.pullToRefresh?.isRefreshing?:false)) VISIBLE else GONE
        hideErrorLayout()
        viewmodel?.getFeeds()?.observe(this,Observer{
            it.onSuccess {response->
                binding?.progressBarCyclic?.visibility= GONE
                binding?.pullToRefresh?.isRefreshing=false
                binding?.feedRv?.adapter?.let {
                    (it as FeedsListAdapter).updateData(response.feedList)
                }
                supportActionBar?.setTitle(response.title);
            }
            it.onFailure {
                binding?.progressBarCyclic?.visibility= GONE
                binding?.pullToRefresh?.isRefreshing=false
                var errMsg = (it as Exception).getErrorMessage()
                showErrorLayout(errMsg)
            }

        })
    }

    private fun hideErrorLayout() {
        binding?.apply {
            errText?.visibility= GONE
            retryBtn?.visibility= GONE
            feedRv?.visibility= VISIBLE
        }
    }

    private fun showErrorLayout(errMsg: String) {
        binding?.apply {
            errText?.text=errMsg
            errText?.visibility=VISIBLE
            retryBtn?.visibility= VISIBLE
            feedRv?.visibility= GONE
        }
    }
}