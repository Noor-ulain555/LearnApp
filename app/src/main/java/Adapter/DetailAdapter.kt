import Data.propertyEntity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnapp.databinding.DetailSampleBinding

class DetailAdapter(private val context: Context,private val detailList:List<propertyEntity>) :
    RecyclerView.Adapter<DetailAdapter.MyHolder>() {

    inner class MyHolder(val binding: DetailSampleBinding) : RecyclerView.ViewHolder(binding.root)

    // Interface for the edit click listener
    interface EditClickListener {
        fun onEditClick(property: propertyEntity)
    }

    private var editClickListener: EditClickListener? = null

    fun setEditClickListener(listener: EditClickListener) {
        this.editClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = DetailSampleBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return detailList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int,) {
        val property = detailList[position]
        holder.binding.apply {
            NumRoom.text = property.room
            NumbedRoom.text = property.bedrooms
            NumbathRoom.text = property.bathrooms
            floor.text = property.floor
            Area.text = property.city
            LOCATION.text = property.location
            rentsAle.text = property.type
            fur.text = property.interior
            marla.text = property.size
        }

        }
    }

