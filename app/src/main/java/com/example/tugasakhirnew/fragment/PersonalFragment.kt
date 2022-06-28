package com.example.tugasakhirnew.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tugasakhirnew.ProfileActivity
import com.example.tugasakhirnew.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalFragment : Fragment() {

    private lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (ctx as ProfileActivity).getUserId()
    }

}